{
  description = "Entorno de desarrollo para app Kotlin Desktop (Compose Multiplatform), target final: Windows 11";

  inputs = {
    # Canal estable de NixOS 26.05 "Yarara". Usar el branch de canal (nixos-26.05),
    # no "release-26.05" (ese es el branch de pre-integración usado antes de
    # promover el canal; puede tener menos binarios cacheados).
    nixpkgs.url = "github:NixOS/nixpkgs/nixos-26.05";

    flake-utils.url = "github:numtide/flake-utils";
  };

  outputs = { self, nixpkgs, flake-utils }:
    flake-utils.lib.eachDefaultSystem (system:
      let
        pkgs = import nixpkgs {
          inherit system;
          config = {
            allowUnfree = true; # IntelliJ IDEA / JetBrains Runtime usan licencias no-libres
          };
        };

        # JDK elegido para TODO el entorno: Gradle, Kotlin CLI e IntelliJ deben
        # apuntar al mismo JDK para evitar builds inconsistentes.
        # temurin-bin (Eclipse Adoptium) es la distribución de OpenJDK más usada
        # en builds de Gradle/Kotlin y la recomendada por Gradle/JetBrains.
        jdk = pkgs.temurin-bin-21;

        # ------------------------------------------------------------------
        # Gradle 9.0.0 empaquetado a mano vía fetchurl, en vez de usar
        # pkgs.gradle_9 de nixpkgs.
        #
        # Por qué: el atributo `gradle_9` en nixpkgs no siempre corresponde a
        # una versión 9.x real (a veces es solo el nombre de la "línea" que
        # nixpkgs aún no ha actualizado al major release correspondiente).
        # Además, la rama de GitHub `nixos-26.05` puede ir desincronizada del
        # canal oficial promovido vía Hydra, así que fijar un commit tampoco
        # es 100% estable entre máquinas.
        #
        # Bajando el binario oficial directamente desde Gradle (con checksum
        # verificado) obtenemos SIEMPRE la misma versión exacta, en
        # cualquier máquina, sin depender del estado de nixpkgs en ese
        # momento. El checksum es público en https://gradle.org/release-checksums/
        # ------------------------------------------------------------------
        gradleVersion = "9.0.0";
        gradle9 = pkgs.stdenv.mkDerivation rec {
          pname = "gradle";
          version = gradleVersion;

          src = pkgs.fetchurl {
            url = "https://services.gradle.org/distributions/gradle-${version}-bin.zip";
            sha256 = "8fad3d78296ca518113f3d29016617c7f9367dc005f932bd9d93bf45ba46072b";
          };

          nativeBuildInputs = [ pkgs.unzip pkgs.makeWrapper ];

          # No hay fase de build real: solo descomprimimos y wrappeamos el
          # script de lanzamiento para que use el JDK del flake.
          dontBuild = true;
          dontConfigure = true;

          installPhase = ''
            runHook preInstall

            mkdir -p $out
            cp -r gradle-${version}/* $out/

            # El script `bin/gradle` detecta Java vía JAVA_HOME/PATH en tiempo
            # de ejecución; lo wrappeamos para que siempre use el JDK fijado
            # en el flake, sin depender del entorno del usuario.
            wrapProgram $out/bin/gradle \
              --set JAVA_HOME "${jdk.home}" \
              --prefix PATH : "${jdk}/bin"

            runHook postInstall
          '';

          meta = with pkgs.lib; {
            description = "Gradle ${version} (binario oficial, empaquetado vía fetchurl)";
            homepage = "https://gradle.org";
            license = licenses.asl20;
            platforms = platforms.unix;
          };
        };

        # ------------------------------------------------------------------
        # Librerías nativas que Compose Desktop (Skiko/Skia) y AWT/Swing
        # cargan dinámicamente en tiempo de ejecución vía JNI.
        #
        # IMPORTANTE: estas NO son herramientas que se invoquen desde la
        # terminal, así que NO van en `packages` (eso solo resuelve PATH).
        # La JVM las busca vía LD_LIBRARY_PATH, así que se inyectan
        # explícitamente ahí con `lib.makeLibraryPath` más abajo.
        # ------------------------------------------------------------------
        runtimeLibs = with pkgs; [
          sqlite
          libGL
          # mesa            # solo si libGL no basta (drivers de software/Vulkan);
                             # agrega bastante peso al store, déjalo comentado
                             # salvo que lo necesites de verdad.
          libx11
          libxext
          libxi
          libxrandr
          libxrender
          libxtst
          libxcursor
          libxcomposite
          fontconfig
          freetype
          glib
        ];

      in
      {
        devShells.default = pkgs.mkShell {
          # "packages" es el nombre moderno recomendado por mkShell (alias de
          # nativeBuildInputs). Se usa para herramientas que se EJECUTAN dentro
          # del shell (compiladores, build tools, CLIs), no para librerías que
          # se enlazan en un artefacto final. Como este devShell no produce un
          # derivation con binarios enlazados contra libs de sistema, no
          # necesitamos buildInputs aquí.
          packages = [
            jdk

            # Kotlin CLI (kotlinc, kotlin, kotlin-dce-js, etc.). Útil para usar
            # el compilador/REPL fuera de Gradle y para que kotlinc coincida
            # con la versión del plugin de Gradle del proyecto. No es
            # estrictamente necesario si solo usarás el Kotlin Gradle Plugin
            # a través de ./gradlew, pero es liviano y conveniente tenerlo.
            pkgs.kotlin

            # Gradle 9.0.0 real, empaquetado directamente desde el binario
            # oficial (ver definición de `gradle9` más arriba). Reemplaza al
            # `pkgs.gradle`/`pkgs.gradle_9` de nixpkgs para evitar el desfase
            # de versión entre nixpkgs y el canal del sistema.
            gradle9

            # IDE. Ver sección "IntelliJ IDEA" en la explicación: esta es la
            # opción reproducible vía Nix; alternativa: JetBrains Toolbox
            # fuera de Nix si preferís autoactualización.
            # pkgs.jetbrains.idea-community

            # pkgs.git
          ];

          # JAVA_HOME apuntando exactamente al mismo derivation de JDK que se
          # usa en "packages", para que Gradle, kotlinc e IntelliJ (si se
          # configura para usarlo) resuelvan el mismo JDK.
          #
          # IMPORTANTE: NO hardcodear la ruta interna (ej. "${jdk}/lib/openjdk"):
          # el layout interno de cada paquete JDK puede variar. La forma
          # correcta y estable es usar el atributo `passthru.home` que TODOS
          # los JDKs de nixpkgs exponen justamente para este propósito.
          JAVA_HOME = "${jdk.home}";
          JDK_HOME = "${jdk.home}";  # algunas herramientas usan JDK_HOME en vez de JAVA_HOME

          # Esto es lo que realmente le permite a Skiko/AWT encontrar libGL,
          # libX11, fontconfig, etc. en tiempo de ejecución dentro de NixOS.
          # Sin esto, tenerlas en `packages` no sirve de nada para Compose
          # Desktop (ver nota arriba en `runtimeLibs`).
          LD_LIBRARY_PATH = pkgs.lib.makeLibraryPath runtimeLibs;

          shellHook = ''
            export PATH="${jdk}/bin:$PATH"
            echo "Entorno Kotlin Desktop (target Windows 11) - NixOS 26.05 Yarara"
            echo "JAVA_HOME = $JAVA_HOME"
            echo "Verificá versiones con: java --version | kotlinc -version | gradle -v | ./gradlew --version"
          '';
        };
      });
}

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

        # El proyecto declara en gradle/gradle-daemon-jvm.properties que su
        # Daemon JVM debe ser Java 25, vendor "Azul Zulu" (feature de Gradle
        # 9: Daemon JVM auto-discovery/provisioning). Si el JDK del flake no
        # coincide EXACTAMENTE en versión+vendor, Gradle intentará
        # auto-descargar el JDK correcto por internet en cada máquina,
        # rompiendo la reproducibilidad. Por eso usamos zulu (no temurin-bin)
        # y en la versión 25.
        jdk = pkgs.zulu25;

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
            # El unpackPhase de Nix ya nos deja parados DENTRO del único
            # directorio raíz del zip (gradle-${version}/), así que copiamos
            # el contenido del cwd actual, no "gradle-${version}/*".
            cp -r . "$out"/

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

        runtimeLibs = with pkgs; [
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
          stdenv.cc.cc.lib
        ];

      in
      {
        devShells.default = pkgs.mkShell {
          packages = [
            jdk
            pkgs.kotlin
            pkgs.sqlite
            gradle9

            # IDE. Ver sección "IntelliJ IDEA" en la explicación: esta es la
            # opción reproducible vía Nix; alternativa: JetBrains Toolbox
            # fuera de Nix si preferís autoactualización.
            # pkgs.jetbrains.idea-community
            # pkgs.git
          ];

          JAVA_HOME = "${jdk.home}";
          JDK_HOME = "${jdk.home}";  # algunas herramientas usan JDK_HOME en vez de JAVA_HOME

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

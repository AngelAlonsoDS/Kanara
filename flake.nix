{
  description = "Kanara - Kotlin Multiplatform (JVM/Desktop, Compose)";

  inputs = {
    nixpkgs.url = "github:NixOS/nixpkgs/nixos-unstable";
    flake-utils.url = "github:numtide/flake-utils";
  };

  outputs = { self, nixpkgs, flake-utils }:
    flake-utils.lib.eachDefaultSystem (system:
      let
        pkgs = import nixpkgs { inherit system; };

        jdk = pkgs.jdk25;
      in
      {
        devShells.default = pkgs.mkShell {
          buildInputs = [
            jdk
            pkgs.gradle_9
            pkgs.kotlin
            pkgs.sqlite

            pkgs.libGL
            pkgs.mesa
            pkgs.libx11
            pkgs.libxest
            pkgs.libxi
            pkgs.libxrand
            pkgs.libXrender
            pkgs.libXtst
            pkgs.libxcursor
            pkgs.libxcomposite

            pkgs.fontconfig
            pkgs.freetype
            pkgs.glib
          ];

          JAVA_HOME = "${jdk.home}";
          JDK_HOME = "${jdk.home}";

          LD_LIBRARY_PATH = pkgs.lib.makeLibraryPath [
            pkgs.libGL
            pkgs.xorg.libX11
            pkgs.xorg.libXrender
            pkgs.xorg.libXtst
            pkgs.xorg.libXi
            pkgs.fontconfig
            pkgs.freetype
          ];

          shellHook = ''
            export PATH="${jdk}/bin:$PATH"
            echo "Entorno Kanara listo"
            echo "  JDK:    $(java -version 2>&1 | head -n1)"
            echo "  Gradle (shell): $(gradle -v | grep Gradle | head -n1)"
            echo "  Nota: usa ./gradlew para respetar la versión fijada por el proyecto"
          '';
        };
      }
    );
}

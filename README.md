# Camera Low (Fabric 1.21.11)

Tecla **P** (en juego, sin pantallas abiertas) abre el slider de altura de cámara.

## Compilar

### Opción A — GitHub Actions (sin instalar nada)
1. Subí esta carpeta a un repo de GitHub.
2. Pestaña **Actions** → workflow `build` (corre solo en cada push).
3. Al terminar, descargá el artifact `camera-low-jar` → `camera-low-2.1.0.jar`.

### Opción B — Local
Requiere JDK 25 instalado (para correr Gradle; el mod igual se compila para Java 21).
- Windows: `gradlew.bat build`
- Linux/macOS: `./gradlew build`

El jar queda en `build/libs/camera-low-2.1.0.jar` (no uses el `-sources.jar`).

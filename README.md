# Sistema de Gestión de Torneos y Ligas Deportivas 

Este proyecto es una aplicación de escritorio desarrollada en **Java** para la gestión integral de competiciones deportivas. Permite la administración completa de jugadores, equipos, jornadas, simulación de partidos y la generación automática de la clasificación.

Desarrollado como parte del 3º Reto (Erronka) en el centro de FP Txurdinaga.

## Características Principales

* **Gestión de Entidades:**
  * Creación y administración de **Jugadores** (`Jokalariak`) y **Equipos** (`Taldeak`).
  * Asignación de jugadores a sus respectivos equipos.
* **Sistema de Competición:**
  * Generación y visualización de **Jornadas** (`Jardunaldiak`) y **Partidos** (`Partiduak`).
  * Simulación de resultados de los partidos (`PartiduakJolastu`).
  * Cálculo y actualización automática de la **Clasificación** (`Klasifikazioa`).
* **Persistencia de Datos:**
  * Guardado automático del estado de la aplicación mediante serialización de objetos en archivos `.dat` (`jokalariak.dat`, `taldeak.dat`).
  * Importación y exportación de datos en formato **XML** (`IrteeraSarreraXML`).
* **Interfaz Gráfica (GUI):**
  * Navegación principal mediante menús (`Menua`).
  * Ventanas interactivas para la visualización de datos de la liga (`VentanaJornadas`).

## Tecnologías Utilizadas

* **Lenguaje:** Java (POO)
* **Interfaz Gráfica:** Java Swing / AWT
* **Almacenamiento:** Ficheros binarios (Serialización) y manejo de DOM para XML.
* **Documentación:** JavaDoc (incluida en el directorio `/doc/`)
* **Entorno de Desarrollo:** Optimizado para Eclipse IDE (incluye `.classpath` y `.project`).

## Estructura del Proyecto

El código fuente está dividido de forma modular para separar la lógica de negocio, las vistas y los tests:
├── src/
│   ├── main/          # Lógica principal, controladores de ventanas e I/O (XML)
│   ├── objektuak/     # Modelos de datos (Pertsona, Jokalariak, Taldeak, Partiduak...)
│   └── frogak/        # Batería de pruebas unitarias (Tests)
├── doc/               # Documentación JavaDoc autogenerada
├── *.dat              # Archivos locales de persistencia de datos
└── log.txt            # Registro de eventos de la aplicación

## Instalación y Ejecución

**Opción 1: A través de Eclipse IDE (Recomendado)**
1. Clona o descarga el repositorio en tu máquina local.
2. Abre Eclipse y selecciona `File > Import > General > Existing Projects into Workspace`.
3. Selecciona la carpeta raíz del proyecto y haz clic en `Finish`.
4. Ejecuta la clase principal (`src/main/Menua.java` o el archivo que contenga el método `main`) para lanzar la aplicación.

**Opción 2: Desde la terminal**
1. Navega hasta el directorio `src`.
2. Compila los archivos Java: `javac main/*.java objektuak/*.java`
3. Ejecuta la clase principal: `java main.Menua` (asegúrate de especificar la clase de entrada correcta).

## Pruebas (Testing)

El proyecto cuenta con un paquete dedicado a la validación de la lógica del sistema. Para ejecutar los tests, navega al paquete `src/frogak/` donde encontrarás clases como:

* `JokalariaSortuFroga.java`
* `TaldeakSortuFroga.java`
* `KlasifikazioaFroga.java`
* `IrteeraSarreraXMLFroga.java`

## Documentación

La documentación técnica del código ha sido generada con JavaDoc. Puedes consultarla abriendo el archivo `doc/index.html` en cualquier navegador web.

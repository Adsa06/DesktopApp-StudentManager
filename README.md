# PEC 4 Acceso a Datos

Este es un proyecto de una mini-aplicación Java (Maven) que gestione una base de datos sobre MongoDB.
La tematica de esta aplicacion es la de un gestor de estudiantes

| Detalle              | Información       |
|----------------------|-------------------|
| **Autor**            | Aitor de Santos   |
| **Fecha de inicio**  | 30/01/2026        |
| **Última revisión**  | v1 06/02/2026     |

## Estructura del Proyecto

El proyecto está dividido en varios archivos Java organizados en:

```plaintext
proyecto-maven/
├── README.md                           # Este archivo
├── .vscode/                            # Configuraciones para Visual Studio Code
├── target/                             # Carpeta con el código compilado del src/
├── src/main/                           # Código fuente y recursos
│   ├── java/dev/adsa/    
│   │     ├── Main.java                 # Archivo principal del programa
│   │     ├── controller/               # Controladores del programa
│   │     ├── model/                    # Paquete para los modelos de clase
│   │     ├── service/                  # Paquete para la logica de negocio del programa
│   │     ├── dao/                      # Dao de estudiantes
│   │     ├── exceptions/               # Paquete para las excepciones
│   │     └── utilities/                # Paquete de utilidades para el programa
│   └── resources/view/
│           ├── student_item_view.fxml  # Vista de un item de estudiante
│           └── main_menu_view.fxml     # Vista principal del programa
└── pom.xml                             # Archivo de configuración de Maven
```

## Requisitos del Sistema

- **Java Development Kit (JDK)** versión 21 o superior.
- **Maven** para la gestión de dependencias.
- **MongoDB** para la base de datos.
- Una terminal en el sistema (CMD, PowerShell, etc.).

## Compilación y Ejecución

### Compilación con Maven

1. Abre una terminal y dirigete a la raiz del proyecto `student-manager/`.
2. Ejecuta el siguiente comando para compilar el proyecto:
   ```bash
   mvn clean compile
   ```
3. Esto generará los archivos `.class` en la carpeta `target/`.

### Ejecución de la Aplicacion

1. Antes de la ejecucion necesitas [configurar la base de datos](#base-de-datos) (Si el enlace no funciona ve al apartado de "Base de Datos")
2. Abre una terminales y dirigete a la raiz del proyecto `student-manager/`.
3. En la terminal, ejecuta:
   ```bash
   mvn clean javafx:run
   ```

### Creacion del javadoc con maven

1. Abre una terminal  y dirigete a la raiz del proyecto `proyecto-maven/`.
2. Ejecuta el siguiente comando para crear la documentacion del proyecto:
   ```bash
   mvn javadoc:javadoc
   ```
3. Esto generará los archivos necesarios en la carpeta `target\reports\apidocs\`.

## Descripción de Funcionamiento

El proyecto consiste en una aplicación Java (arquitectura MVC con MongoDB y JavaFX) que gestiona los alumnos de un instituto.
La aplicación permite al usuario interactuar mediante una interfaz gráfica y realizar diversas operaciones sobre la base de datos de forma segura aplicando CRUD.

### Operaciones CRUD

- **Crear**: Agregar un nuevo alumno a la base de datos, mediante un formulario a la izquierda de la pantalla.
- **Leer**: Obtener información de alumnos de la base de datos, se mostraran en una forma de lista.
- **Actualizar**: Modificar la información de un alumno en la base de datos, mediante un formulario.
- **Borrar**: Eliminar un alumno de la base de datos, seleccionando de la lista los alumnos a eliminar.

## Base de Datos

El proyecto utiliza una base de datos MongoDB para almacenar información sobre los estudiantes.
Asegurate de tener MongoDB Compass instalado y configurado en tu equipo.

### Configuración de la Base de Datos

1. Ejecuta el script SQL que se encuentra el la raiz del proyecto.
2. Configura las credenciales en el archivo `MongoUtil.java` del paquete `utilities`.:
   ```java
      private static final String URI = "mongodb://localhost:27017";
      private static final String DB_NAME = "student_manager";
   ```

## Notas Adicionales

Este proyecto está diseñado para aprender y mejorar en:

- **Modularización**: Descomposición del programa en métodos.
- **Documentación**: Creación de un análisis técnico del software.
- **Uso de herramientas modernas**: Familiarización con Visual Studio Code, JDK 21, Maven, MongoDB y JavaFX.
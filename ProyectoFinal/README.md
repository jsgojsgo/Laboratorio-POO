# README

**El archivo JAR se encuentra en ProyectoFinal/dist/**

**Explicación del proyecto:**
El proyecto consiste en un sistema de punto de venta y gestión para gimnasio desarrollado en Java y JavaFX. Organiza sus funciones por roles: cliente nuevo, socio y staff, con interfaz gráfica, persistencia en archivos CSV, filtrado dinámico en tablas, reportes y herramientas reutilizables para validación y diálogos modales:
- Consulta de planes para clientes nuevos.
- Acceso y gestión para socios.
- Administración de clientes, membresías, clases, inventario y accesos para staff.
- Generación de reportes de entradas.
- Validaciones de formularios, búsqueda dinámica y estilos visuales con CSS.


El código fuente está organizado por paquetes dentro de ProyectoFinal/src:
- `view/`: ventanas y pantallas JavaFX.
- `controller/`: conexión entre vistas y lógica.
- `service/`: reglas de negocio.
- `repository/`: lectura y escritura de archivos.
- `model/`: clases del dominio.
- `util/`: utilidades reutilizables.
- `resources/`: bases de datos CSV y estilos.

Toda la documentación técnica y explicaciones del sistema se encuentra en ProyectoFinal/docs:
- Manual técnico
- Manual de usuario
- Bitácora IA
- Diagramas UML
- El video explicativo se encuentra aquí: para el https://drive.google.com/file/d/11DhhkPyN3m2YwtpM0bU2jiHCtigsxz8u/view?usp=sharing

**Requisitos**
- Java 8 o superior
- Compilador de Java (`javac`) y terminal para ejecutar

**Guía para ejecutar**
**Opción 1:**
1. Abrir el archivo `ProyectoFinal.java`
2. Compilarlo y ejecutarlo

**Opción 2:**
1. Ejecutar el Jar en `dist/`

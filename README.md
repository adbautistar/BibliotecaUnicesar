# Biblioteca Unicesar

Sistema de gestión de biblioteca desarrollado como proyecto práctico de **Programación III** (POO + Arquitectura en Capas).

## Descripción

Gestiona libros, usuarios y préstamos de una biblioteca. La persistencia se realiza mediante archivos de texto plano, sin base de datos ni interfaz gráfica.

## Tecnologías

- Java 21
- Maven
- NetBeans
- Consola como presentación
- Archivos `.txt` como persistencia

## Arquitectura

El proyecto sigue una **arquitectura en capas**:

```
VIEW
 ↓
SERVICE
 ↓
REPOSITORY
 ↓
ARCHIVOS TXT
```

- **View**: interacción por consola. Recibe entradas y muestra resultados.
- **Service**: reglas de negocio y coordinación de casos de uso.
- **Repository**: contrato y acceso a la persistencia.
- **Model**: entidades del dominio (`Person`, `User`, `Librarian`, `Book`, `Loan`).

> **Nota:** `Librarian` modela la herencia de `Person` junto con `User`, pero todavía no tiene `Repository`, `Service` ni opción en el menú de consola. Es un modelo pendiente de conectar, no código muerto por error — se deja documentado aquí hasta que el alcance del proyecto requiera gestionar bibliotecarios.

## Estructura del proyecto

```
BibliotecaUnicesar/
├── src/main/java/biblioteca/bibliotecaunicesar/
│   ├── model/
│   ├── repository/
│   ├── service/
│   ├── view/
│   ├── exception/
│   └── util/
├── data/
│   ├── libros.txt
│   ├── usuarios.txt
│   └── prestamos.txt
├── pom.xml
└── README.md
```

## Funcionalidades

- Gestión de libros.
- Gestión de usuarios.
- Registro de préstamos.
- Registro de devoluciones.
- Consulta de disponibilidad.

## Cómo ejecutar

Desde NetBeans: abrir el proyecto y ejecutar `BibliotecaUnicesar.java` (clase `main`).

## Convenciones Git

- Ramas: `feature/nombre`, `fix/nombre`, `refactor/nombre`, `docs/nombre`, `test/nombre`, `chore/nombre`.
- Commits: [Conventional Commits](https://www.conventionalcommits.org/) en inglés (`feat:`, `fix:`, `docs:`, `refactor:`, `test:`, `chore:`).
- Cada funcionalidad se integra mediante Pull Request, con título y descripción en inglés.

## Aprendizajes

Este proyecto integra progresivamente: POO, herencia, abstracción, polimorfismo, interfaces, arquitectura en capas, persistencia en archivos, manejo de excepciones y flujo colaborativo con Git/GitHub.

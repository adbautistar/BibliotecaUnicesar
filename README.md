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

- Gestión de libros (registrar, listar).
- Gestión de usuarios (registrar, listar).
- Registro de préstamos, con validación de reglas de negocio (usuario existe, libro disponible, fecha válida, límite de préstamos).
- Registro de devoluciones, liberando la disponibilidad del libro.
- Consulta de préstamos registrados.
- Manejo de errores de negocio mediante excepciones propias del dominio (`UserNotFoundException`, `BookNotAvailableException`, `InvalidLoanException`).

## Cómo ejecutar

**Desde NetBeans:** abrir el proyecto y ejecutar `BibliotecaUnicesar.java` (clase `main`).

**Desde línea de comandos** (con Maven instalado, o el Maven embebido de NetBeans):

```bash
mvn compile exec:java
```

Al ejecutarse, se crean automáticamente los archivos `data/libros.txt`, `data/usuarios.txt` y `data/prestamos.txt` si no existen.

## Flujo de una operación

Caso de uso principal: **registrar un préstamo**, mostrando cómo atraviesa las 4 capas:

```
Usuario elige la opción 3 en el menú
 ↓
MainMenu → LoanView.registerLoan()
 ↓
LoanView pide userId, bookId y días de préstamo
 ↓
LoanService.registerLoan(userId, bookId, loanDate, returnDate)
 ↓
 Valida en orden:
 1. ¿El usuario existe?            → si no, UserNotFoundException
 2. ¿El libro existe?               → si no, InvalidLoanException
 3. ¿El libro está disponible?      → si no, BookNotAvailableException
 4. ¿La fecha de devolución es válida?
 5. ¿El usuario está bajo su límite de préstamos?
 ↓
LoanRepository.save(loan) + BookRepository.update(book)
 ↓
LoanRepositoryImpl / BookRepositoryImpl → FileManager
 ↓
data/prestamos.txt y data/libros.txt (disponible = false)
 ↓
Resultado (préstamo registrado, o el mensaje de la excepción) se imprime en consola
```

La devolución (`LoanView.registerReturn()`) sigue el mismo camino en sentido inverso: marca el préstamo como devuelto y vuelve a poner `disponible = true` en el libro.

## Convenciones Git

- Todo el código, nombres de clases/métodos/variables, ramas, commits y Pull Requests están en **inglés**. Los comentarios dentro del código están en **español**, como guía pedagógica.
- Commits: [Conventional Commits](https://www.conventionalcommits.org/) (`feat:`, `fix:`, `docs:`, `refactor:`, `test:`, `chore:`), pequeños y con un cambio lógico cada uno.

## Ramas

Convención: `feature/nombre`, `fix/nombre`, `refactor/nombre`, `docs/nombre`, `test/nombre`, `chore/nombre`.

Ramas usadas en este proyecto (todas ya fusionadas a `main` y eliminadas):

| Rama | Fase |
|---|---|
| `feature/model-dominio` | Modelado del dominio (Person, User, Librarian, Book, Loan) |
| `feature/repository-contracts` | Interfaces de Repository |
| `feature/file-persistence` | Persistencia en archivos `.txt` |
| `feature/business-services` | Reglas de negocio (Service) |
| `feature/console-view` | Vista por consola |
| `feature/error-handling` | Excepciones de dominio |
| `docs/architecture-audit` | Auditoría arquitectónica |
| `docs/project-documentation` | Esta actualización del README |

## Pull Requests

Cada funcionalidad se integró mediante un Pull Request individual, con título y descripción en inglés, siguiendo el formato:

```
## Summary
- Qué se implementó (una línea por cambio relevante).

## Architecture / Notes (cuando aplica)
- Decisiones de diseño relevantes.

## Validation
- Cómo se verificó (compilación, prueba manual, etc.).
```

## Aprendizajes

Este proyecto integra progresivamente: POO, herencia, abstracción, polimorfismo, interfaces, arquitectura en capas, persistencia en archivos, manejo de excepciones y flujo colaborativo con Git/GitHub.

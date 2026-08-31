# PROMPT MAESTRO --- PROYECTO BIBLIOTECA

## Programación III --- Java + POO + Arquitectura en Capas + Git/GitHub

## 0. REGLA GLOBAL DE IDIOMA Y NOMENCLATURA

Todo el desarrollo técnico del proyecto debe realizarse en **inglés**.

Esto incluye:

-   Nombres de clases.
-   Interfaces.
-   Atributos.
-   Métodos.
-   Variables.
-   Constantes.
-   Paquetes.
-   Archivos y carpetas del código.
-   Ramas de Git.
-   Mensajes de commit.
-   Pull Requests.
-   Identificadores y nombres técnicos.

Ejemplos:

``` text
Book
User
Loan
Person
BookRepository
LoanService
ConsoleView
feature/book-repository
feat: create book repository
```

### Comentarios del código

Los **comentarios dentro del código deben escribirse en español** y
utilizarse como guía pedagógica para los estudiantes.

Ejemplo:

``` java
public class Book {

    // Identificador único del libro
    private Long id;

    // Título registrado en el catálogo
    private String title;
}
```

No mezclar idiomas en los identificadores técnicos.

## 1. CONTEXTO

Estoy desarrollando con mis estudiantes de **Programación III** un
proyecto práctico en **Java**.

Los estudiantes ya han trabajado:

-   POO.
-   Clases y objetos.
-   Encapsulamiento.
-   Herencia.
-   Clases abstractas.
-   Interfaces.
-   Polimorfismo.
-   Relaciones entre clases.
-   Colecciones de Java.
-   `LocalDate`.
-   Git y GitHub.
-   Commits, ramas y Pull Requests.
-   Buenas prácticas de trabajo colaborativo.

Ahora estamos trabajando **Arquitectura en Capas**.

### Restricción académica

Los estudiantes todavía **NO han desarrollado una interfaz gráfica ni
persistencia mediante base de datos**.

Por tanto, este proyecto debe utilizar:

-   Java.
-   Maven.
-   NetBeans.
-   Consola como presentación.
-   Archivos de texto plano como persistencia.
-   Git y GitHub.

**NO utilizar:** JavaFX, Swing como interfaz gráfica, MySQL/MariaDB,
JDBC para BD, JPA/Hibernate, Spring/Spring Boot, APIs REST o
microservicios.

------------------------------------------------------------------------

# 2. PROYECTO

## Sistema de Gestión de Biblioteca

El sistema gestionará:

-   Libros.
-   Usuarios.
-   Préstamos.
-   Devoluciones.
-   Disponibilidad de libros.

Los datos se persistirán en archivos `.txt`.

Ejemplo:

``` text
data/
├── libros.txt
├── usuarios.txt
└── prestamos.txt
```

La consola representa nuestra **View** y la persistencia en archivos
estará encapsulada en el Repository.

------------------------------------------------------------------------

# 3. OBJETIVO PEDAGÓGICO

El objetivo no es solamente terminar una aplicación.

El objetivo es integrar:

``` text
POO
↓
CLASES Y OBJETOS
↓
HERENCIA / ABSTRACCIÓN / POLIMORFISMO
↓
INTERFACES
↓
ARQUITECTURA EN CAPAS
↓
PERSISTENCIA EN ARCHIVOS
↓
CONSOLA
↓
GIT
↓
GITHUB
↓
PULL REQUEST
```

Cada fase debe aportar una pieza funcional y demostrar qué concepto
aprendido se está aplicando.

------------------------------------------------------------------------

# 4. ARQUITECTURA OBJETIVO

``` text
biblioteca-app/
│
├── src/main/java/com/biblioteca/
│   ├── model/
│   ├── dto/
│   ├── repository/
│   ├── service/
│   ├── view/
│   ├── exception/
│   └── util/
│
├── data/
│   ├── libros.txt
│   ├── usuarios.txt
│   └── prestamos.txt
│
├── README.md
├── .gitignore
└── pom.xml
```

### model

Representa el dominio:

-   `Persona`
-   `Usuario`
-   `Bibliotecario`
-   `Libro`
-   `Prestamo`

### dto

Transporta información cuando sea necesario.

Ejemplo:

-   `PrestamoDTO`

**DTO no es una capa.**

### repository

Define el contrato de persistencia:

-   `LibroRepository`
-   `UsuarioRepository`
-   `PrestamoRepository`

### service

Contiene las reglas de negocio:

-   `LibroService`
-   `UsuarioService`
-   `PrestamoService`

### view

Interacción por consola:

-   `MenuPrincipal`
-   `LibroView`
-   `UsuarioView`
-   `PrestamoView`

### exception

Excepciones del dominio:

-   `LibroNoDisponibleException`
-   `UsuarioNoEncontradoException`
-   `PrestamoInvalidoException`

### util

Utilidades técnicas, por ejemplo:

-   `FileManager`

------------------------------------------------------------------------

# 5. PRINCIPIO ARQUITECTÓNICO

Flujo:

``` text
VIEW
 ↓
SERVICE
 ↓
REPOSITORY
 ↓
ARCHIVO TXT
```

Respuesta:

``` text
ARCHIVO TXT
 ↑
REPOSITORY
 ↑
SERVICE
 ↑
VIEW
```

### Regla

Cada capa debe hacer únicamente aquello que le corresponde.

**View:** recibe y muestra información.

**Service:** aplica reglas de negocio y coordina.

**Repository:** almacena, recupera, actualiza y elimina.

**Archivo:** almacena físicamente la información.

------------------------------------------------------------------------

# 6. DAO VS REPOSITORY

Utilizar preferentemente el término **Repository**.

Explicar que:

-   DAO significa Data Access Object.
-   Repository es una abstracción de persistencia/acceso.
-   En proyectos sencillos pueden tener responsabilidades similares.
-   No son necesariamente sinónimos universales.
-   Lo importante es la responsabilidad y el nivel de abstracción.

El Service debe depender de la interfaz, no de la implementación.

------------------------------------------------------------------------

# 7. REGLAS PARA CLAUDE CODE

Claude Code debe actuar como:

> **arquitecto, desarrollador asistente y tutor técnico.**

No debe generar todo el proyecto de una sola vez.

Debe trabajar:

1.  Fase por fase.
2.  Funcionalidad por funcionalidad.
3.  Con commits pequeños.
4.  Con ramas.
5.  Con Pull Requests.
6.  Explicando decisiones importantes.
7.  Verificando compilación y funcionamiento después de cada cambio.

## Regla fundamental

**NO avanzar automáticamente a la siguiente fase.**

Al terminar cada fase:

-   revisar;
-   ejecutar pruebas;
-   mostrar cambios;
-   explicar conceptos aplicados;
-   proponer commit;
-   proponer Pull Request;
-   esperar autorización.

------------------------------------------------------------------------

# 8. GIT Y GITHUB

Flujo:

``` text
main
 ↓
crear rama
 ↓
desarrollar
 ↓
probar
 ↓
git status
 ↓
git diff
 ↓
git add
 ↓
git commit
 ↓
git push
 ↓
Pull Request
 ↓
Code Review
 ↓
Merge
 ↓
eliminar rama
 ↓
actualizar main
```

## Convenciones de ramas

``` text
feature/nombre
fix/nombre
refactor/nombre
docs/nombre
test/nombre
chore/nombre
```

Ejemplos:

``` text
feature/model-dominio
feature/repository-libro
feature/file-persistence
feature/service-prestamo
feature/console-view
fix/validacion-prestamo
docs/readme-arquitectura
```

## Conventional Commits

``` text
feat: create book model
feat: create book repository interface
feat: implement book persistence
feat: add loan service
feat: add main console menu

fix: correct book availability validation

docs: actualizar README

refactor: separate persistence logic

test: add loan service tests

chore: configure Maven project
```

Los commits deben estar **completamente en inglés**, ser pequeños,
claros y representar un cambio lógico.

------------------------------------------------------------------------

# 9. PULL REQUEST

Cada funcionalidad importante debe integrarse mediante Pull Request. El
**título y la descripción del Pull Request deben estar completamente en
inglés**.

### Título

``` text
feat: implement book persistence
```

### Descripción

Incluir:

-   Qué se implementó.
-   Por qué.
-   Clases modificadas.
-   Cómo se probó.
-   Conceptos de POO aplicados.
-   Principios arquitectónicos aplicados.

### Ejemplo de Pull Request

**Title:**

``` text
feat: implement book persistence
```

**Description:**

``` text
## Summary
- Implemented file-based persistence for books.
- Added BookRepository implementation.
- Added file management utility.

## Validation
- Project compiles successfully.
- Book records can be saved and loaded from books.txt.
```

### Checklist

``` text
- [ ] El proyecto compila.
- [ ] La funcionalidad fue probada.
- [ ] Se respetan las responsabilidades.
- [ ] El commit sigue la convención.
- [ ] No hay archivos innecesarios.
- [ ] README actualizado si corresponde.
```

------------------------------------------------------------------------

# 10. FASE 0 --- PREPARACIÓN

### Objetivo

Crear la base técnica.

### Actividades

-   Crear proyecto Maven.
-   Configurar Java 21.
-   Crear `.gitignore`.
-   Crear README.
-   Crear estructura inicial.
-   Inicializar Git.
-   Crear repositorio GitHub.
-   Configurar `main`.

### Commit

``` text
chore: initialize Maven project
```

### Regla

Si ya existe un proyecto, **primero inspeccionarlo y no eliminar ni
sobrescribir código sin autorización**.

------------------------------------------------------------------------

# 11. FASE 1 --- MODELAR EL DOMINIO

### Objetivo

Aplicar POO al dominio de biblioteca.

### Entidades iniciales

``` text
Persona
 ├── Usuario
 └── Bibliotecario

Libro

Prestamo
```

No crear herencia artificial. Cada relación debe justificarse
pedagógicamente.

### Conceptos

-   Clase.
-   Objeto.
-   Encapsulamiento.
-   Herencia.
-   Abstracción.
-   Polimorfismo.
-   Asociación/composición.
-   `LocalDate`.

### Rama

``` text
feature/model-dominio
```

### Commits posibles

``` text
feat: create abstract person class
feat: create user class
feat: create librarian class
feat: create book entity
feat: create loan entity
```

Crear Pull Request y esperar aprobación.

------------------------------------------------------------------------

# 12. FASE 2 --- DISEÑAR LOS CONTRATOS

### Objetivo

Aplicar interfaces y abstracción.

Crear:

``` java
LibroRepository
UsuarioRepository
PrestamoRepository
```

Operaciones posibles:

``` text
guardar
buscarPorId
listar
actualizar
eliminar
```

### Conceptos

-   Interfaces.
-   Abstracción.
-   Polimorfismo.
-   Bajo acoplamiento.
-   Contratos.

### Rama

``` text
feature/repository-contracts
```

### Commit

``` text
feat: definir contratos de repository
```

------------------------------------------------------------------------

# 13. FASE 3 --- PERSISTENCIA CON ARCHIVOS

### Objetivo

Implementar persistencia sin base de datos.

Archivos:

``` text
data/
├── libros.txt
├── usuarios.txt
└── prestamos.txt
```

Implementaciones:

``` text
LibroRepositoryImpl
UsuarioRepositoryImpl
PrestamoRepositoryImpl
```

Utilizar Java estándar para:

-   leer;
-   escribir;
-   actualizar;
-   eliminar;
-   convertir texto ↔ objetos.

Crear `FileManager` si aporta una responsabilidad clara.

### Regla arquitectónica

El Service NO debe conocer detalles como:

``` text
FileReader
FileWriter
BufferedReader
BufferedWriter
Path
Files
```

Esos detalles pertenecen a la persistencia.

### Rama

``` text
feature/file-persistence
```

### Commits posibles

``` text
feat: create file management utility
feat: implement book persistence
feat: implement user persistence
feat: implement loan persistence
```

------------------------------------------------------------------------

# 14. FASE 4 --- LÓGICA DE NEGOCIO

### Objetivo

Construir:

``` text
LibroService
UsuarioService
PrestamoService
```

El Service debe:

-   validar reglas;
-   coordinar repositories;
-   manejar excepciones de negocio;
-   ejecutar casos de uso.

## Caso principal

``` text
Usuario existe?
 ↓
Libro existe?
 ↓
Libro disponible?
 ↓
Usuario puede prestar?
 ↓
Registrar préstamo
 ↓
Actualizar disponibilidad
```

Las reglas de negocio deben permanecer en Service.

### Rama

``` text
feature/business-services
```

### Commits

``` text
feat: create book service
feat: create user service
feat: implement loan rules
feat: create loan service
```

------------------------------------------------------------------------

# 15. FASE 5 --- DTO

### Objetivo

Introducir DTO solamente cuando aporte valor.

Crear:

``` text
PrestamoDTO
```

Ejemplo:

``` text
usuarioId
libroId
fechaPrestamo
fechaDevolucion
```

Explicar:

> DTO transporta información; no es una capa.

### Rama

``` text
feature/dto-prestamo
```

### Commit

``` text
feat: add loan DTO
```

------------------------------------------------------------------------

# 16. FASE 6 --- VIEW POR CONSOLA

### Objetivo

Crear la presentación sin interfaz gráfica.

La consola será la View.

Crear:

``` text
MenuPrincipal
LibroView
UsuarioView
PrestamoView
```

Ejemplo:

``` text
================================
       SISTEMA BIBLIOTECA
================================

1. Gestionar libros
2. Gestionar usuarios
3. Registrar préstamo
4. Registrar devolución
5. Consultar préstamos
0. Salir
```

La View:

-   recibe entradas;
-   muestra resultados;
-   llama al Service.

La View NO debe:

-   leer archivos;
-   escribir archivos;
-   ejecutar reglas de negocio;
-   acceder directamente al Repository.

### Rama

``` text
feature/console-view
```

### Commits

``` text
feat: create main console menu
feat: add book console view
feat: add user console view
feat: add loan console view
```

------------------------------------------------------------------------

# 17. FASE 7 --- INTEGRACIÓN

### Objetivo

Conectar todas las capas.

``` text
Usuario
 ↓
VIEW
 ↓
SERVICE
 ↓
REPOSITORY
 ↓
ARCHIVO TXT
```

Ejemplo:

``` text
Registrar préstamo
 ↓
PrestamoView
 ↓
PrestamoService
 ↓
PrestamoRepository
 ↓
prestamos.txt
```

Respuesta por el mismo camino.

### Rama

``` text
feature/integration
```

### Commit

``` text
feat: integrate complete loan workflow
```

------------------------------------------------------------------------

# 18. FASE 8 --- EXCEPCIONES Y ROBUSTEZ

### Objetivo

Manejar errores de forma clara.

Ejemplos:

``` text
LibroNoDisponibleException
UsuarioNoEncontradoException
PrestamoInvalidoException
```

Revisar:

-   entradas inválidas;
-   archivos inexistentes;
-   errores de lectura/escritura;
-   reglas de negocio;
-   mensajes de error;
-   duplicación.

### Rama

``` text
feature/error-handling
```

### Commit

``` text
feat: add domain exceptions
```

------------------------------------------------------------------------

# 19. FASE 9 --- PRUEBAS

### Objetivo

Probar principalmente la lógica de negocio.

Si JUnit ya forma parte del curso, probar:

-   `LibroService`.
-   `PrestamoService`.
-   disponibilidad.
-   validaciones.
-   casos exitosos.
-   casos de error.

No introducir testing excesivamente avanzado.

### Rama

``` text
test/service-layer
```

### Commit

``` text
test: add loan service tests
```

------------------------------------------------------------------------

# 20. FASE 10 --- AUDITORÍA ARQUITECTÓNICA

Claude Code debe revisar:

### View

¿Accede directamente a archivos?

### Service

¿Contiene reglas de negocio?

### Repository

¿Encapsula persistencia?

### Model

¿Representa correctamente el dominio?

### DTO

¿Se utiliza cuando realmente aporta valor?

### Dependencias

¿Las capas están correctamente relacionadas?

### Acoplamiento

¿Hay dependencias innecesarias?

### Cohesión

¿Cada clase tiene una responsabilidad clara?

Entregar un informe:

``` text
ARQUITECTURA
✓ View
✓ Service
✓ Repository
✓ Model
✓ DTO

PROBLEMAS
...

RECOMENDACIONES
...
```

------------------------------------------------------------------------

# 21. FASE 11 --- DOCUMENTACIÓN

Actualizar `README.md`.

Debe incluir:

-   Descripción.
-   Tecnologías.
-   Arquitectura.
-   Estructura del proyecto.
-   Funcionalidades.
-   Cómo ejecutar.
-   Flujo de una operación.
-   Convenciones Git.
-   Ramas.
-   Pull Requests.
-   Aprendizajes.

Diagrama mínimo:

``` text
VIEW
 ↓
SERVICE
 ↓
REPOSITORY
 ↓
ARCHIVOS TXT
```

------------------------------------------------------------------------

# 22. FASE 12 --- ENTREGA FINAL

Verificar:

``` text
[ ] Compila.
[ ] Inicia correctamente.
[ ] Los archivos se crean.
[ ] Se pueden gestionar libros.
[ ] Se pueden gestionar usuarios.
[ ] Funcionan préstamos.
[ ] Funcionan devoluciones.
[ ] Funcionan las reglas.
[ ] La arquitectura se respeta.
[ ] No existen accesos indebidos entre capas.
[ ] Git tiene historial claro.
[ ] Se utilizaron ramas.
[ ] Los Pull Requests están documentados.
[ ] README actualizado.
[ ] .gitignore correcto.
```

Crear tag:

``` text
v1.0.0
```

------------------------------------------------------------------------

# 23. CASO DE USO PRINCIPAL

## REGISTRAR PRÉSTAMO

Entrada:

``` text
Usuario
Libro
Fecha de préstamo
Fecha de devolución
```

Reglas:

``` text
El usuario debe existir.
El libro debe existir.
El libro debe estar disponible.
El usuario no debe tener restricciones.
La fecha de devolución debe ser válida.
```

Flujo:

``` text
VIEW
 ↓
PrestamoService
 ↓
validaciones
 ↓
PrestamoRepository
 ↓
prestamos.txt
 ↓
actualización del libro
 ↓
resultado
 ↓
VIEW
```

Este caso debe servir para demostrar toda la arquitectura.

------------------------------------------------------------------------

# 24. REGLAS DE CÓDIGO

Todo código generado debe:

-   ser Java correcto;
-   usar nombres claros;
-   respetar convenciones Java;
-   aplicar encapsulamiento;
-   evitar duplicación innecesaria;
-   utilizar interfaces con sentido;
-   mantener responsabilidades separadas;
-   evitar sobreingeniería;
-   ser comprensible para Programación III.

No generar código avanzado únicamente para demostrar conocimiento.

------------------------------------------------------------------------

# 25. REGLAS PEDAGÓGICAS

Cuando sea relevante, Claude Code debe explicar:

-   ¿Por qué existe esta clase?
-   ¿Por qué pertenece a esta capa?
-   ¿Por qué usamos una interfaz?
-   ¿Por qué Repository?
-   ¿Por qué no colocar esta lógica en View?
-   ¿Por qué la persistencia está aislada?
-   ¿Qué concepto de POO estamos aplicando?
-   ¿Qué principio arquitectónico estamos aplicando?
-   ¿Qué cambio permite hacer esta separación?

El proyecto debe funcionar como demostración práctica de la teoría.

------------------------------------------------------------------------

# 26. MINECRAFT

Minecraft puede utilizarse únicamente como analogía pedagógica.

Ejemplo:

``` text
VIEW
Jugador interactúa.

SERVICE
Reglas del juego.

REPOSITORY
Almacenamiento.

MODEL
Objetos del mundo.
```

No incorporar Minecraft al código real.

------------------------------------------------------------------------

# 27. REGLA CONTRA LA SOBREINGENIERÍA

No agregar automáticamente:

-   Spring.
-   Spring Boot.
-   Hibernate.
-   JPA.
-   MySQL.
-   APIs REST.
-   Docker.
-   Microservicios.
-   Patrones complejos.
-   Frameworks innecesarios.

El proyecto debe crecer de acuerdo con lo que los estudiantes ya conocen
y con una necesidad pedagógica concreta.

------------------------------------------------------------------------

# 28. FORMATO DE RESPUESTA DE CLAUDE CODE

Al iniciar cada fase:

``` text
FASE ACTUAL
Objetivo:

CONCEPTOS DE PROGRAMACIÓN III
- ...
- ...

CAMBIOS PROPUESTOS
- ...
- ...

CLASES / ARCHIVOS
- ...
- ...

ARQUITECTURA
- ...

IMPLEMENTACIÓN
...

VALIDACIÓN
...

GIT
Rama:
Commit sugerido:

PULL REQUEST
Título:
Descripción:

ESTADO
✓ Completado
✗ Pendiente

¿Continuamos con la siguiente fase?
```

**No avanzar automáticamente.**

------------------------------------------------------------------------

# 29. PRIMERA INSTRUCCIÓN PARA CLAUDE CODE

Al recibir este documento:

1.  Leer completamente el contexto.
2.  Inspeccionar el proyecto actual.
3.  Verificar si ya existe código.
4.  Verificar Git.
5.  Verificar rama actual.
6.  Verificar JDK.
7.  Verificar Maven.
8.  No modificar código todavía.
9.  Presentar diagnóstico inicial.
10. Comparar el estado actual con la arquitectura objetivo.
11. Proponer FASE 0.
12. Esperar autorización.

No asumir que el proyecto está vacío.

No eliminar archivos existentes.

No sobrescribir código sin explicar primero el motivo.

No crear funcionalidades de fases posteriores anticipadamente.

------------------------------------------------------------------------

# 30. FILOSOFÍA GENERAL

El proyecto debe mostrar esta evolución:

``` text
Tenemos clases
 ↓
Tenemos un problema
 ↓
Necesitamos organizar responsabilidades
 ↓
Definimos capas
 ↓
Definimos contratos
 ↓
Implementamos persistencia
 ↓
Aplicamos reglas de negocio
 ↓
Construimos la View
 ↓
Conectamos todo
 ↓
Versionamos cada cambio
 ↓
Revisamos mediante Pull Request
 ↓
Tenemos un sistema completo
```

El objetivo final no es memorizar:

> View, Service, Repository, Model.

El objetivo es poder responder:

> **¿Por qué cada componente existe, qué responsabilidad tiene y cómo se
> relaciona con los demás?**

------------------------------------------------------------------------

# 31. CRITERIO FINAL

Cada funcionalidad debe responder:

### 1. ¿Qué problema resuelve?

### 2. ¿En qué capa pertenece?

### 3. ¿Qué concepto de POO estamos aplicando?

### 4. ¿Cómo queda registrado el cambio en Git?

Si no puede responderse claramente estas cuatro preguntas, detener la
implementación y analizar antes de continuar.

------------------------------------------------------------------------

# FIN DEL PROMPT MAESTRO

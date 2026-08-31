package biblioteca.bibliotecaunicesar.model;

// Representa un libro del catálogo de la biblioteca.
// No hereda de Person porque no existe una relación "es-un" entre ambos:
// un libro es una entidad independiente del dominio, no un tipo de persona.
public class Book {

    // Identificador único del libro
    private Long id;

    // Título registrado en el catálogo
    private String title;

    // Autor del libro
    private String author;

    // Código ISBN del libro
    private String isbn;

    // Indica si el libro está disponible para préstamo
    private boolean available;

    public Book(Long id, String title, String author, String isbn) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.available = true; // Todo libro nuevo inicia disponible
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

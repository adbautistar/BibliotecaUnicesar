package biblioteca.bibliotecaunicesar.exception;

// Se lanza cuando se intenta prestar un libro que ya está prestado
// (no disponible). Representa una regla de negocio del préstamo.
public class BookNotAvailableException extends RuntimeException {

    public BookNotAvailableException(String message) {
        super(message);
    }
}

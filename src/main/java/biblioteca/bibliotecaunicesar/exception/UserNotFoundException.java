package biblioteca.bibliotecaunicesar.exception;

// Se lanza cuando se busca o se referencia un usuario que no existe en el sistema.
// Es una excepción no verificada (extiende RuntimeException) porque representa
// una violación de una regla de negocio, no un error recuperable de entrada/salida.
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }
}

package biblioteca.bibliotecaunicesar.exception;

// Se lanza cuando un préstamo no cumple las condiciones necesarias para
// registrarse o gestionarse: libro inexistente, fecha de devolución
// inválida, usuario que alcanzó su límite de préstamos, o préstamo
// inexistente al intentar registrar una devolución.
public class InvalidLoanException extends RuntimeException {

    public InvalidLoanException(String message) {
        super(message);
    }
}

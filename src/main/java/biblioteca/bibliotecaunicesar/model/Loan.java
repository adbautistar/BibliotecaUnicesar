package biblioteca.bibliotecaunicesar.model;

import java.time.LocalDate;

// Representa el préstamo de un libro a un usuario.
// Se relaciona por asociación con User y Book: un préstamo no existe
// sin un usuario ni sin un libro, pero ninguno de los dos "es" un préstamo.
public class Loan {

    // Identificador único del préstamo
    private Long id;

    // Usuario que solicita el préstamo
    private User user;

    // Libro que se presta
    private Book book;

    // Fecha en la que se realiza el préstamo
    private LocalDate loanDate;

    // Fecha límite para devolver el libro
    private LocalDate returnDate;

    // Indica si el préstamo ya fue devuelto
    private boolean returned;

    public Loan(Long id, User user, Book book, LocalDate loanDate, LocalDate returnDate) {
        this.id = id;
        this.user = user;
        this.book = book;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
        this.returned = false; // Todo préstamo nuevo inicia sin devolver
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }
}

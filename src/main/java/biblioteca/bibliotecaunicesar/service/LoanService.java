package biblioteca.bibliotecaunicesar.service;

import biblioteca.bibliotecaunicesar.model.Book;
import biblioteca.bibliotecaunicesar.model.Loan;
import biblioteca.bibliotecaunicesar.model.User;
import biblioteca.bibliotecaunicesar.repository.BookRepository;
import biblioteca.bibliotecaunicesar.repository.LoanRepository;
import biblioteca.bibliotecaunicesar.repository.UserRepository;
import java.time.LocalDate;
import java.util.List;

// Contiene las reglas de negocio del préstamo: es el caso de uso principal
// del sistema y el que demuestra el flujo completo de la arquitectura en capas.
// Coordina tres repositorios (Loan, User, Book) mediante sus interfaces.
public class LoanService {

    private final LoanRepository loanRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public LoanService(LoanRepository loanRepository, UserRepository userRepository, BookRepository bookRepository) {
        this.loanRepository = loanRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    // Registra un préstamo aplicando las reglas del negocio:
    // el usuario debe existir, el libro debe existir y estar disponible,
    // la fecha de devolución debe ser válida y el usuario no debe haber
    // alcanzado su límite de préstamos activos.
    public Loan registerLoan(Long userId, Long bookId, LocalDate loanDate, LocalDate returnDate) {
        User user = userRepository.findById(userId);
        if (user == null) {
            throw new IllegalArgumentException("El usuario no existe.");
        }

        Book book = bookRepository.findById(bookId);
        if (book == null) {
            throw new IllegalArgumentException("El libro no existe.");
        }

        if (!book.isAvailable()) {
            throw new IllegalStateException("El libro no está disponible para préstamo.");
        }

        if (!returnDate.isAfter(loanDate)) {
            throw new IllegalArgumentException("La fecha de devolución debe ser posterior a la fecha de préstamo.");
        }

        if (countActiveLoans(user) >= user.getLoanLimit()) {
            throw new IllegalStateException("El usuario alcanzó su límite de préstamos activos.");
        }

        Long id = generateNextId();
        Loan loan = new Loan(id, user, book, loanDate, returnDate);
        loanRepository.save(loan);

        book.setAvailable(false);
        bookRepository.update(book);

        return loan;
    }

    // Registra la devolución de un préstamo y libera la disponibilidad del libro
    public void returnLoan(Long loanId) {
        Loan loan = loanRepository.findById(loanId);
        if (loan == null) {
            throw new IllegalArgumentException("El préstamo no existe.");
        }

        loan.setReturned(true);
        loanRepository.update(loan);

        Book book = loan.getBook();
        book.setAvailable(true);
        bookRepository.update(book);
    }

    public List<Loan> listLoans() {
        return loanRepository.findAll();
    }

    // Cuenta los préstamos activos (no devueltos) de un usuario
    private long countActiveLoans(User user) {
        long count = 0;
        for (Loan loan : loanRepository.findAll()) {
            if (loan.getUser().getId().equals(user.getId()) && !loan.isReturned()) {
                count++;
            }
        }
        return count;
    }

    // Genera el siguiente id disponible a partir del mayor id existente
    private Long generateNextId() {
        long maxId = 0;
        for (Loan loan : loanRepository.findAll()) {
            if (loan.getId() > maxId) {
                maxId = loan.getId();
            }
        }
        return maxId + 1;
    }
}

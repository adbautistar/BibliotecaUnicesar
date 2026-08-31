package biblioteca.bibliotecaunicesar.repository;

import biblioteca.bibliotecaunicesar.model.Book;
import biblioteca.bibliotecaunicesar.model.Loan;
import biblioteca.bibliotecaunicesar.model.User;
import biblioteca.bibliotecaunicesar.util.FileManager;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// Implementación de LoanRepository. A diferencia de Book y User, un Loan no se
// basta a sí mismo para reconstruirse desde una línea de texto: necesita el
// User y el Book completos. Por eso depende (composición) de UserRepository y
// BookRepository, para resolver esas referencias a partir de sus IDs al leer.
public class LoanRepositoryImpl implements LoanRepository {

    private static final String FILE_PATH = "data/prestamos.txt";
    private static final String DELIMITER = ";";

    private final FileManager fileManager;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public LoanRepositoryImpl(FileManager fileManager, UserRepository userRepository, BookRepository bookRepository) {
        this.fileManager = fileManager;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void save(Loan loan) {
        fileManager.appendLine(FILE_PATH, toLine(loan));
    }

    @Override
    public Loan findById(Long id) {
        for (Loan loan : findAll()) {
            if (loan.getId().equals(id)) {
                return loan;
            }
        }
        return null;
    }

    @Override
    public List<Loan> findAll() {
        List<Loan> loans = new ArrayList<>();
        for (String line : fileManager.readLines(FILE_PATH)) {
            if (!line.isBlank()) {
                loans.add(fromLine(line));
            }
        }
        return loans;
    }

    @Override
    public void update(Loan loan) {
        List<String> lines = new ArrayList<>();
        for (Loan current : findAll()) {
            lines.add(current.getId().equals(loan.getId()) ? toLine(loan) : toLine(current));
        }
        fileManager.writeLines(FILE_PATH, lines);
    }

    @Override
    public void delete(Long id) {
        List<String> lines = new ArrayList<>();
        for (Loan loan : findAll()) {
            if (!loan.getId().equals(id)) {
                lines.add(toLine(loan));
            }
        }
        fileManager.writeLines(FILE_PATH, lines);
    }

    // Convierte un Loan en una línea de texto: id;userId;bookId;loanDate;returnDate;returned
    private String toLine(Loan loan) {
        return loan.getId() + DELIMITER + loan.getUser().getId() + DELIMITER
                + loan.getBook().getId() + DELIMITER + loan.getLoanDate() + DELIMITER
                + loan.getReturnDate() + DELIMITER + loan.isReturned();
    }

    // Reconstruye un Loan a partir de una línea de texto, resolviendo User y Book por su id
    private Loan fromLine(String line) {
        String[] fields = line.split(DELIMITER, -1);
        Long id = Long.parseLong(fields[0]);
        User user = userRepository.findById(Long.parseLong(fields[1]));
        Book book = bookRepository.findById(Long.parseLong(fields[2]));
        LocalDate loanDate = LocalDate.parse(fields[3]);
        LocalDate returnDate = LocalDate.parse(fields[4]);
        Loan loan = new Loan(id, user, book, loanDate, returnDate);
        loan.setReturned(Boolean.parseBoolean(fields[5]));
        return loan;
    }
}

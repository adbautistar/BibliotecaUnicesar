package biblioteca.bibliotecaunicesar.view;

import biblioteca.bibliotecaunicesar.exception.BookNotAvailableException;
import biblioteca.bibliotecaunicesar.exception.InvalidLoanException;
import biblioteca.bibliotecaunicesar.exception.UserNotFoundException;
import biblioteca.bibliotecaunicesar.model.Book;
import biblioteca.bibliotecaunicesar.model.Loan;
import biblioteca.bibliotecaunicesar.model.User;
import biblioteca.bibliotecaunicesar.service.BookService;
import biblioteca.bibliotecaunicesar.service.LoanService;
import biblioteca.bibliotecaunicesar.service.UserService;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

// Vista de consola para préstamos. También depende de BookService y
// UserService, pero únicamente para MOSTRAR las listas de apoyo antes de
// pedir un id: nunca accede a Repository ni aplica reglas de negocio,
// esas viven en LoanService.
public class LoanView {

    private final Scanner scanner;
    private final LoanService loanService;
    private final BookService bookService;
    private final UserService userService;

    public LoanView(Scanner scanner, LoanService loanService, BookService bookService, UserService userService) {
        this.scanner = scanner;
        this.loanService = loanService;
        this.bookService = bookService;
        this.userService = userService;
    }

    public void registerLoan() {
        System.out.println("Usuarios registrados:");
        for (User user : userService.listUsers()) {
            System.out.println(user.getId() + " - " + user.getName());
        }
        System.out.print("Id de usuario: ");
        Long userId = readLong();

        System.out.println("Libros disponibles:");
        for (Book book : bookService.listBooks()) {
            if (book.isAvailable()) {
                System.out.println(book.getId() + " - " + book.getTitle());
            }
        }
        System.out.print("Id de libro: ");
        Long bookId = readLong();

        System.out.print("Días de préstamo: ");
        int days = readInt();

        try {
            Loan loan = loanService.registerLoan(userId, bookId, LocalDate.now(), LocalDate.now().plusDays(days));
            System.out.println("Préstamo registrado con id " + loan.getId());
        } catch (UserNotFoundException | BookNotAvailableException | InvalidLoanException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void registerReturn() {
        System.out.print("Id de préstamo: ");
        Long loanId = readLong();

        try {
            loanService.returnLoan(loanId);
            System.out.println("Devolución registrada.");
        } catch (InvalidLoanException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void listLoans() {
        List<Loan> loans = loanService.listLoans();
        if (loans.isEmpty()) {
            System.out.println("No hay préstamos registrados.");
            return;
        }
        for (Loan loan : loans) {
            System.out.println(loan.getId() + " - " + loan.getUser().getName() + " / " + loan.getBook().getTitle()
                    + " - Devuelto: " + loan.isReturned());
        }
    }

    private Long readLong() {
        try {
            return Long.parseLong(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1L;
        }
    }

    private int readInt() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}

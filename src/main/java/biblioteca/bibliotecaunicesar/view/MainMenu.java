package biblioteca.bibliotecaunicesar.view;

import biblioteca.bibliotecaunicesar.repository.BookRepository;
import biblioteca.bibliotecaunicesar.repository.BookRepositoryImpl;
import biblioteca.bibliotecaunicesar.repository.LoanRepository;
import biblioteca.bibliotecaunicesar.repository.LoanRepositoryImpl;
import biblioteca.bibliotecaunicesar.repository.UserRepository;
import biblioteca.bibliotecaunicesar.repository.UserRepositoryImpl;
import biblioteca.bibliotecaunicesar.service.BookService;
import biblioteca.bibliotecaunicesar.service.LoanService;
import biblioteca.bibliotecaunicesar.service.UserService;
import biblioteca.bibliotecaunicesar.util.FileManager;
import java.util.Scanner;

// Punto de entrada de la presentación por consola. Ensambla las
// dependencias de todas las capas (Repository -> Service -> View) y
// muestra el menú principal. No contiene reglas de negocio ni toca archivos:
// esa es la garantía de que cada capa cumple solo su responsabilidad.
public class MainMenu {

    private final Scanner scanner;
    private final BookView bookView;
    private final UserView userView;
    private final LoanView loanView;

    public MainMenu() {
        this.scanner = new Scanner(System.in);

        FileManager fileManager = new FileManager();
        BookRepository bookRepository = new BookRepositoryImpl(fileManager);
        UserRepository userRepository = new UserRepositoryImpl(fileManager);
        LoanRepository loanRepository = new LoanRepositoryImpl(fileManager, userRepository, bookRepository);

        BookService bookService = new BookService(bookRepository);
        UserService userService = new UserService(userRepository);
        LoanService loanService = new LoanService(loanRepository, userRepository, bookRepository);

        this.bookView = new BookView(scanner, bookService);
        this.userView = new UserView(scanner, userService);
        this.loanView = new LoanView(scanner, loanService, bookService, userService);
    }

    public void start() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n================================");
            System.out.println("       SISTEMA BIBLIOTECA");
            System.out.println("================================");
            System.out.println("1. Gestionar libros");
            System.out.println("2. Gestionar usuarios");
            System.out.println("3. Registrar préstamo");
            System.out.println("4. Registrar devolución");
            System.out.println("5. Consultar préstamos");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            String option = scanner.nextLine();

            switch (option) {
                case "1" -> bookView.showMenu();
                case "2" -> userView.showMenu();
                case "3" -> loanView.registerLoan();
                case "4" -> loanView.registerReturn();
                case "5" -> loanView.listLoans();
                case "0" -> exit = true;
                default -> System.out.println("Opción inválida.");
            }
        }
        System.out.println("¡Hasta pronto!");
    }
}

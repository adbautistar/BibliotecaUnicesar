package biblioteca.bibliotecaunicesar.view;

import biblioteca.bibliotecaunicesar.model.Book;
import biblioteca.bibliotecaunicesar.service.BookService;
import java.util.List;
import java.util.Scanner;

// Vista de consola para gestionar libros. Solo recibe entradas, muestra
// resultados y llama al Service: nunca conoce Repository ni el archivo .txt,
// ni aplica reglas de negocio (eso ya lo valida BookService).
public class BookView {

    private final Scanner scanner;
    private final BookService bookService;

    public BookView(Scanner scanner, BookService bookService) {
        this.scanner = scanner;
        this.bookService = bookService;
    }

    public void showMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n--- GESTIONAR LIBROS ---");
            System.out.println("1. Registrar libro");
            System.out.println("2. Listar libros");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");
            String option = scanner.nextLine();

            switch (option) {
                case "1" -> registerBook();
                case "2" -> listBooks();
                case "0" -> exit = true;
                default -> System.out.println("Opción inválida.");
            }
        }
    }

    private void registerBook() {
        System.out.print("Título: ");
        String title = scanner.nextLine();
        System.out.print("Autor: ");
        String author = scanner.nextLine();
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();

        try {
            Book book = bookService.registerBook(title, author, isbn);
            System.out.println("Libro registrado con id " + book.getId());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void listBooks() {
        List<Book> books = bookService.listBooks();
        if (books.isEmpty()) {
            System.out.println("No hay libros registrados.");
            return;
        }
        for (Book book : books) {
            System.out.println(book.getId() + " - " + book.getTitle() + " (" + book.getAuthor() + ") - "
                    + (book.isAvailable() ? "Disponible" : "Prestado"));
        }
    }
}

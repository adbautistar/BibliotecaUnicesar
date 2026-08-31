package biblioteca.bibliotecaunicesar.service;

import biblioteca.bibliotecaunicesar.model.Book;
import biblioteca.bibliotecaunicesar.repository.BookRepository;
import java.util.List;

// Contiene las reglas de negocio relacionadas con los libros.
// Depende de la interfaz BookRepository, nunca de BookRepositoryImpl:
// así el Service no sabe (ni le importa) que la persistencia es un archivo .txt.
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Registra un nuevo libro, validando los datos mínimos y generando su id
    public Book registerBook(String title, String author, String isbn) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("El título del libro es obligatorio.");
        }
        if (author == null || author.isBlank()) {
            throw new IllegalArgumentException("El autor del libro es obligatorio.");
        }

        Long id = generateNextId();
        Book book = new Book(id, title, author, isbn);
        bookRepository.save(book);
        return book;
    }

    public Book findBook(Long id) {
        return bookRepository.findById(id);
    }

    public List<Book> listBooks() {
        return bookRepository.findAll();
    }

    public void removeBook(Long id) {
        bookRepository.delete(id);
    }

    // Genera el siguiente id disponible a partir del mayor id existente.
    // Esta es una decisión de negocio, no una responsabilidad del archivo de texto.
    private Long generateNextId() {
        long maxId = 0;
        for (Book book : bookRepository.findAll()) {
            if (book.getId() > maxId) {
                maxId = book.getId();
            }
        }
        return maxId + 1;
    }
}

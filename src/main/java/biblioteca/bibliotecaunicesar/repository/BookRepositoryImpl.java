package biblioteca.bibliotecaunicesar.repository;

import biblioteca.bibliotecaunicesar.model.Book;
import biblioteca.bibliotecaunicesar.util.FileManager;
import java.util.ArrayList;
import java.util.List;

// Implementación de BookRepository que persiste los libros en un archivo de texto
// plano. Aquí sí se trabaja con la idea de "archivo", pero siempre a través de
// FileManager: esta clase nunca usa FileReader/FileWriter/Files directamente.
public class BookRepositoryImpl implements BookRepository {

    private static final String FILE_PATH = "data/libros.txt";
    private static final String DELIMITER = ";";

    private final FileManager fileManager;

    public BookRepositoryImpl(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    @Override
    public void save(Book book) {
        fileManager.appendLine(FILE_PATH, toLine(book));
    }

    @Override
    public Book findById(Long id) {
        for (Book book : findAll()) {
            if (book.getId().equals(id)) {
                return book;
            }
        }
        return null;
    }

    @Override
    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        for (String line : fileManager.readLines(FILE_PATH)) {
            if (!line.isBlank()) {
                books.add(fromLine(line));
            }
        }
        return books;
    }

    @Override
    public void update(Book book) {
        List<String> lines = new ArrayList<>();
        for (Book current : findAll()) {
            lines.add(current.getId().equals(book.getId()) ? toLine(book) : toLine(current));
        }
        fileManager.writeLines(FILE_PATH, lines);
    }

    @Override
    public void delete(Long id) {
        List<String> lines = new ArrayList<>();
        for (Book book : findAll()) {
            if (!book.getId().equals(id)) {
                lines.add(toLine(book));
            }
        }
        fileManager.writeLines(FILE_PATH, lines);
    }

    // Convierte un Book en una línea de texto: id;title;author;isbn;available
    private String toLine(Book book) {
        return book.getId() + DELIMITER + book.getTitle() + DELIMITER
                + book.getAuthor() + DELIMITER + book.getIsbn() + DELIMITER
                + book.isAvailable();
    }

    // Reconstruye un Book a partir de una línea de texto
    private Book fromLine(String line) {
        String[] fields = line.split(DELIMITER, -1);
        Book book = new Book(Long.parseLong(fields[0]), fields[1], fields[2], fields[3]);
        book.setAvailable(Boolean.parseBoolean(fields[4]));
        return book;
    }
}

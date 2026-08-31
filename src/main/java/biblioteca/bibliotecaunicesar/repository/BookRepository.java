package biblioteca.bibliotecaunicesar.repository;

import biblioteca.bibliotecaunicesar.model.Book;
import java.util.List;

// Contrato de persistencia para Book. Es una interfaz porque el Service
// no debe conocer CÓMO se guardan los libros (archivo de texto, memoria, etc.),
// solo QUÉ operaciones puede realizar. Esto es bajo acoplamiento: el Service
// depende de esta abstracción, no de una implementación concreta.
public interface BookRepository {

    // Guarda un nuevo libro
    void save(Book book);

    // Busca un libro por su identificador; retorna null si no existe
    Book findById(Long id);

    // Retorna todos los libros registrados
    List<Book> findAll();

    // Actualiza los datos de un libro existente
    void update(Book book);

    // Elimina un libro por su identificador
    void delete(Long id);
}

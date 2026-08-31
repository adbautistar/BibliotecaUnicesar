package biblioteca.bibliotecaunicesar.repository;

import biblioteca.bibliotecaunicesar.model.User;
import java.util.List;

// Contrato de persistencia para User. Igual que BookRepository, separa
// "qué se puede hacer" (el contrato) de "cómo se hace" (la implementación
// futura con archivos de texto).
public interface UserRepository {

    // Guarda un nuevo usuario
    void save(User user);

    // Busca un usuario por su identificador; retorna null si no existe
    User findById(Long id);

    // Retorna todos los usuarios registrados
    List<User> findAll();

    // Actualiza los datos de un usuario existente
    void update(User user);

    // Elimina un usuario por su identificador
    void delete(Long id);
}

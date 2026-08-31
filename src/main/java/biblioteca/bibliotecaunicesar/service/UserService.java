package biblioteca.bibliotecaunicesar.service;

import biblioteca.bibliotecaunicesar.model.User;
import biblioteca.bibliotecaunicesar.repository.UserRepository;
import java.util.List;

// Contiene las reglas de negocio relacionadas con los usuarios.
// Al igual que BookService, depende de la interfaz UserRepository.
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Registra un nuevo usuario, validando los datos mínimos y generando su id
    public User registerUser(String name, String documentId, int loanLimit) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("El nombre del usuario es obligatorio.");
        }
        if (loanLimit <= 0) {
            throw new IllegalArgumentException("El límite de préstamos debe ser mayor a cero.");
        }

        Long id = generateNextId();
        User user = new User(id, name, documentId, loanLimit);
        userRepository.save(user);
        return user;
    }

    public User findUser(Long id) {
        return userRepository.findById(id);
    }

    public List<User> listUsers() {
        return userRepository.findAll();
    }

    public void removeUser(Long id) {
        userRepository.delete(id);
    }

    // Genera el siguiente id disponible a partir del mayor id existente
    private Long generateNextId() {
        long maxId = 0;
        for (User user : userRepository.findAll()) {
            if (user.getId() > maxId) {
                maxId = user.getId();
            }
        }
        return maxId + 1;
    }
}

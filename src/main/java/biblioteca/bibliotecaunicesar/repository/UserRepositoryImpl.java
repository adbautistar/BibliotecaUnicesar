package biblioteca.bibliotecaunicesar.repository;

import biblioteca.bibliotecaunicesar.model.User;
import biblioteca.bibliotecaunicesar.util.FileManager;
import java.util.ArrayList;
import java.util.List;

// Implementación de UserRepository que persiste los usuarios en un archivo
// de texto plano, siguiendo el mismo esquema que BookRepositoryImpl.
public class UserRepositoryImpl implements UserRepository {

    private static final String FILE_PATH = "data/usuarios.txt";
    private static final String DELIMITER = ";";

    private final FileManager fileManager;

    public UserRepositoryImpl(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    @Override
    public void save(User user) {
        fileManager.appendLine(FILE_PATH, toLine(user));
    }

    @Override
    public User findById(Long id) {
        for (User user : findAll()) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        for (String line : fileManager.readLines(FILE_PATH)) {
            if (!line.isBlank()) {
                users.add(fromLine(line));
            }
        }
        return users;
    }

    @Override
    public void update(User user) {
        List<String> lines = new ArrayList<>();
        for (User current : findAll()) {
            lines.add(current.getId().equals(user.getId()) ? toLine(user) : toLine(current));
        }
        fileManager.writeLines(FILE_PATH, lines);
    }

    @Override
    public void delete(Long id) {
        List<String> lines = new ArrayList<>();
        for (User user : findAll()) {
            if (!user.getId().equals(id)) {
                lines.add(toLine(user));
            }
        }
        fileManager.writeLines(FILE_PATH, lines);
    }

    // Convierte un User en una línea de texto: id;name;documentId;loanLimit
    private String toLine(User user) {
        return user.getId() + DELIMITER + user.getName() + DELIMITER
                + user.getDocumentId() + DELIMITER + user.getLoanLimit();
    }

    // Reconstruye un User a partir de una línea de texto
    private User fromLine(String line) {
        String[] fields = line.split(DELIMITER, -1);
        return new User(Long.parseLong(fields[0]), fields[1], fields[2], Integer.parseInt(fields[3]));
    }
}

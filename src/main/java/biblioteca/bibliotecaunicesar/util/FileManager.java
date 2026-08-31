package biblioteca.bibliotecaunicesar.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

// Utilidad técnica encargada de leer y escribir archivos de texto plano.
// Es la ÚNICA clase del proyecto que debe conocer Files, Path, etc.
// Los RepositoryImpl la usan para no acoplarse directamente al manejo de archivos,
// tal como exige la regla arquitectónica de la persistencia.
public class FileManager {

    // Lee todas las líneas de un archivo. Si el archivo no existe, retorna una
    // lista vacía: así el sistema funciona la primera vez, sin archivos previos.
    public List<String> readLines(String filePath) {
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            return new ArrayList<>();
        }
        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            throw new RuntimeException("Error al leer el archivo: " + filePath, e);
        }
    }

    // Sobrescribe el archivo completo con las líneas dadas.
    // Crea las carpetas necesarias si aún no existen.
    public void writeLines(String filePath, List<String> lines) {
        Path path = Paths.get(filePath);
        try {
            if (path.getParent() != null) {
                Files.createDirectories(path.getParent());
            }
            Files.write(path, lines);
        } catch (IOException e) {
            throw new RuntimeException("Error al escribir el archivo: " + filePath, e);
        }
    }

    // Agrega una única línea al final del archivo (usado al guardar un nuevo registro)
    public void appendLine(String filePath, String line) {
        List<String> lines = readLines(filePath);
        lines.add(line);
        writeLines(filePath, lines);
    }
}

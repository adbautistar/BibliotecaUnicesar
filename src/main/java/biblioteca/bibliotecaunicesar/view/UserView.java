package biblioteca.bibliotecaunicesar.view;

import biblioteca.bibliotecaunicesar.model.User;
import biblioteca.bibliotecaunicesar.service.UserService;
import java.util.List;
import java.util.Scanner;

// Vista de consola para gestionar usuarios. Misma responsabilidad que
// BookView: solo entrada/salida y llamadas al Service.
public class UserView {

    private final Scanner scanner;
    private final UserService userService;

    public UserView(Scanner scanner, UserService userService) {
        this.scanner = scanner;
        this.userService = userService;
    }

    public void showMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n--- GESTIONAR USUARIOS ---");
            System.out.println("1. Registrar usuario");
            System.out.println("2. Listar usuarios");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");
            String option = scanner.nextLine();

            switch (option) {
                case "1" -> registerUser();
                case "2" -> listUsers();
                case "0" -> exit = true;
                default -> System.out.println("Opción inválida.");
            }
        }
    }

    private void registerUser() {
        System.out.print("Nombre: ");
        String name = scanner.nextLine();
        System.out.print("Documento: ");
        String documentId = scanner.nextLine();
        System.out.print("Límite de préstamos: ");
        int loanLimit = readInt();

        try {
            User user = userService.registerUser(name, documentId, loanLimit);
            System.out.println("Usuario registrado con id " + user.getId());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void listUsers() {
        List<User> users = userService.listUsers();
        if (users.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
            return;
        }
        for (User user : users) {
            System.out.println(user.getId() + " - " + user.getName() + " (" + user.getDocumentId() + ") - "
                    + "Límite: " + user.getLoanLimit());
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

package biblioteca.bibliotecaunicesar.model;

// Librarian: persona encargada de administrar el sistema (registrar libros,
// gestionar préstamos, etc.). Hereda de Person por la misma razón que User:
// comparte los atributos básicos pero cumple un rol distinto dentro del sistema.
public class Librarian extends Person {

    // Código interno del empleado dentro de la biblioteca
    private String employeeCode;

    public Librarian(Long id, String name, String documentId, String employeeCode) {
        super(id, name, documentId);
        this.employeeCode = employeeCode;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    @Override
    public String describeRole() {
        return "Librarian";
    }
}

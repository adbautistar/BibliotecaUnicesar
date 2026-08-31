package biblioteca.bibliotecaunicesar.model;

// Usuario de la biblioteca: puede solicitar préstamos de libros.
// Hereda de Person porque comparte los atributos básicos (id, name, documentId)
// y añade el atributo propio de su rol: el límite de préstamos simultáneos.
public class User extends Person {

    // Cantidad máxima de préstamos activos que puede tener este usuario
    private int loanLimit;

    public User(Long id, String name, String documentId, int loanLimit) {
        super(id, name, documentId);
        this.loanLimit = loanLimit;
    }

    public int getLoanLimit() {
        return loanLimit;
    }

    public void setLoanLimit(int loanLimit) {
        this.loanLimit = loanLimit;
    }

    @Override
    public String describeRole() {
        return "User";
    }
}

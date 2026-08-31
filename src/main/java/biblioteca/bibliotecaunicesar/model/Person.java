package biblioteca.bibliotecaunicesar.model;

// Clase abstracta que representa una persona genérica dentro del sistema.
// Es abstracta porque nunca existirá una "Person" suelta: siempre será
// un User o un Librarian. Así evitamos instanciar objetos sin sentido
// de negocio y centralizamos los atributos comunes a ambos roles.
public abstract class Person {

    // Identificador único de la persona
    private Long id;

    // Nombre completo de la persona
    private String name;

    // Documento de identificación (cédula, DNI, etc.)
    private String documentId;

    public Person(Long id, String name, String documentId) {
        this.id = id;
        this.name = name;
        this.documentId = documentId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    // Cada subclase debe indicar qué rol cumple dentro del sistema (polimorfismo)
    public abstract String describeRole();
}

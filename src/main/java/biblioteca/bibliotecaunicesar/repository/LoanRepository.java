package biblioteca.bibliotecaunicesar.repository;

import biblioteca.bibliotecaunicesar.model.Loan;
import java.util.List;

// Contrato de persistencia para Loan. El PrestamoService (futuro LoanService)
// dependerá de esta interfaz, nunca de una clase concreta que sepa leer o
// escribir archivos: esa responsabilidad pertenece exclusivamente al Repository.
public interface LoanRepository {

    // Guarda un nuevo préstamo
    void save(Loan loan);

    // Busca un préstamo por su identificador; retorna null si no existe
    Loan findById(Long id);

    // Retorna todos los préstamos registrados
    List<Loan> findAll();

    // Actualiza los datos de un préstamo existente
    void update(Loan loan);

    // Elimina un préstamo por su identificador
    void delete(Long id);
}

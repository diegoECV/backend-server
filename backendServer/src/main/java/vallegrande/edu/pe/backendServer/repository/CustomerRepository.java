package vallegrande.edu.pe.backendServer.repository;

import vallegrande.edu.pe.backendServer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}

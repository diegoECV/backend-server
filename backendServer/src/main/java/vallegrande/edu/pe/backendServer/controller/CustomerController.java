package vallegrande.edu.pe.backendServer.controller;

import vallegrande.edu.pe.backendServer.model.Customer;
import vallegrande.edu.pe.backendServer.repository.CustomerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "*")  // Temporal para pruebas (después lo ajustaremos)
public class CustomerController {

    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // 1. Listar todos los clientes (READ)
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // 2. Obtener cliente por ID (READ - específico)
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Integer id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 3. Crear nuevo cliente (CREATE)
    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);
        return ResponseEntity.ok(savedCustomer);
    }

    // 4. Editar cliente (UPDATE)
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Integer id, @RequestBody Customer customerDetails) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            Customer customerToUpdate = customer.get();
            
            // Solo actualizar si el valor no es null
            if (customerDetails.getDni() != null) {
                customerToUpdate.setDni(customerDetails.getDni());
            }
            if (customerDetails.getCellphone() != null) {
                customerToUpdate.setCellphone(customerDetails.getCellphone());
            }
            if (customerDetails.getFirstName() != null) {
                customerToUpdate.setFirstName(customerDetails.getFirstName());
            }
            if (customerDetails.getLastName() != null) {
                customerToUpdate.setLastName(customerDetails.getLastName());
            }
            if (customerDetails.getEmail() != null) {
                customerToUpdate.setEmail(customerDetails.getEmail());
            }
            if (customerDetails.getRol() != null) {
                customerToUpdate.setRol(customerDetails.getRol());
            }
            if (customerDetails.getState() != null) {
                customerToUpdate.setState(customerDetails.getState());
            }
            
            Customer updatedCustomer = customerRepository.save(customerToUpdate);
            return ResponseEntity.ok(updatedCustomer);
        }
        return ResponseEntity.notFound().build();
    }

    // 5. Eliminar cliente (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Integer id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

package pe.edu.tecsup.minishop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.tecsup.minishop.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

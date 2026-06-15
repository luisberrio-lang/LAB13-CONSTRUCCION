package pe.edu.tecsup.minishop.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.tecsup.minishop.exception.ProductNotFoundException;
import pe.edu.tecsup.minishop.model.Product;
import pe.edu.tecsup.minishop.repository.ProductRepository;

import java.util.List;

@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    public Product create(Product product) {
        product.setId(null);
        return productRepository.save(product);
    }

    public Product update(Long id, Product product) {
        Product currentProduct = findById(id);
        applyProductChanges(currentProduct, product);
        return productRepository.save(currentProduct);
    }

    public void delete(Long id) {
        Product currentProduct = findById(id);
        productRepository.delete(currentProduct);
    }

    private void applyProductChanges(Product currentProduct, Product updatedProduct) {
        currentProduct.setName(updatedProduct.getName());
        currentProduct.setDescription(updatedProduct.getDescription());
        currentProduct.setPrice(updatedProduct.getPrice());
        currentProduct.setStock(updatedProduct.getStock());
    }
}

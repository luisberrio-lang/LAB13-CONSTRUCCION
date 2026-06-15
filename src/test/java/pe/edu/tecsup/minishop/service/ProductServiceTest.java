package pe.edu.tecsup.minishop.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pe.edu.tecsup.minishop.exception.ProductNotFoundException;
import pe.edu.tecsup.minishop.model.Product;
import pe.edu.tecsup.minishop.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void findAllReturnsProducts() {
        Product product = new Product(1L, "Laptop", "Development laptop", new BigDecimal("2499.90"), 5);
        when(productRepository.findAll()).thenReturn(List.of(product));

        List<Product> products = productService.findAll();

        assertThat(products).containsExactly(product);
    }

    @Test
    void findByIdReturnsExistingProduct() {
        Product product = new Product(1L, "Mouse", "Wireless mouse", new BigDecimal("79.90"), 10);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Product foundProduct = productService.findById(1L);

        assertThat(foundProduct).isEqualTo(product);
    }

    @Test
    void findByIdThrowsWhenProductDoesNotExist() {
        when(productRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> productService.findById(99L))
                .isInstanceOf(ProductNotFoundException.class)
                .hasMessage("Product with id 99 was not found");
    }

    @Test
    void createClearsIdAndSavesProduct() {
        Product request = new Product(99L, "Keyboard", "Mechanical keyboard", new BigDecimal("149.90"), 7);
        Product saved = new Product(1L, "Keyboard", "Mechanical keyboard", new BigDecimal("149.90"), 7);
        when(productRepository.save(any(Product.class))).thenReturn(saved);

        Product createdProduct = productService.create(request);

        assertThat(createdProduct).isEqualTo(saved);
        assertThat(request.getId()).isNull();
        verify(productRepository).save(request);
    }

    @Test
    void updateModifiesExistingProduct() {
        Product currentProduct = new Product(1L, "Mouse", "Wireless mouse", new BigDecimal("79.90"), 10);
        Product request = new Product(null, "Mouse Pro", "Updated mouse", new BigDecimal("99.90"), 15);
        when(productRepository.findById(1L)).thenReturn(Optional.of(currentProduct));
        when(productRepository.save(currentProduct)).thenReturn(currentProduct);

        Product updatedProduct = productService.update(1L, request);

        assertThat(updatedProduct.getId()).isEqualTo(1L);
        assertThat(updatedProduct.getName()).isEqualTo("Mouse Pro");
        assertThat(updatedProduct.getDescription()).isEqualTo("Updated mouse");
        assertThat(updatedProduct.getPrice()).isEqualByComparingTo("99.90");
        assertThat(updatedProduct.getStock()).isEqualTo(15);
    }

    @Test
    void deleteRemovesExistingProduct() {
        Product product = new Product(1L, "Monitor", "Full HD monitor", new BigDecimal("599.00"), 3);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        productService.delete(1L);

        verify(productRepository).delete(product);
    }
}

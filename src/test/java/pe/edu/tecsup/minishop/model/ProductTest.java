package pe.edu.tecsup.minishop.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class ProductTest {

    @Test
    void equalsReturnsTrueForSameInstance() {
        Product product = new Product(1L, "Laptop", "Development laptop", new BigDecimal("2499.90"), 5);

        assertThat(product).isEqualTo(product);
    }

    @Test
    void equalsReturnsTrueForSameId() {
        Product product = new Product(1L, "Laptop", "Development laptop", new BigDecimal("2499.90"), 5);
        Product sameProduct = new Product(1L, "Laptop Pro", "Updated laptop", new BigDecimal("2999.90"), 3);

        assertThat(product).isEqualTo(sameProduct);
        assertThat(product).hasSameHashCodeAs(sameProduct);
    }

    @Test
    void equalsReturnsFalseForDifferentId() {
        Product product = new Product(1L, "Laptop", "Development laptop", new BigDecimal("2499.90"), 5);
        Product anotherProduct = new Product(2L, "Mouse", "Wireless mouse", new BigDecimal("79.90"), 25);

        assertThat(product).isNotEqualTo(anotherProduct);
    }

    @Test
    void equalsReturnsFalseForDifferentType() {
        Product product = new Product(1L, "Laptop", "Development laptop", new BigDecimal("2499.90"), 5);

        assertThat(product).isNotEqualTo("Laptop");
    }
}

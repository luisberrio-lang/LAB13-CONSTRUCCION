package pe.edu.tecsup.minishop.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ProductIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void findAllReturnsSeedProducts() throws Exception {
        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(3))));
    }

    @Test
    void createProductReturnsCreatedProduct() throws Exception {
        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "Headset",
                                  "description": "USB headset",
                                  "price": 129.90,
                                  "stock": 12
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("Headset"))
                .andExpect(jsonPath("$.price").value(129.90))
                .andExpect(jsonPath("$.stock").value(12));
    }

    @Test
    void createProductWithInvalidPayloadReturnsBadRequest() throws Exception {
        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "",
                                  "description": "Invalid product",
                                  "price": 0,
                                  "stock": -1
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Validation failed"))
                .andExpect(jsonPath("$.fields.name").exists())
                .andExpect(jsonPath("$.fields.price").exists())
                .andExpect(jsonPath("$.fields.stock").exists());
    }

    @Test
    void createProductWithoutPriceReturnsBadRequest() throws Exception {
        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "Speaker",
                                  "description": "Bluetooth speaker",
                                  "stock": 6
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Validation failed"))
                .andExpect(jsonPath("$.fields.price").value("Product price is required"));
    }

    @Test
    void updateExistingProductReturnsUpdatedProduct() throws Exception {
        mockMvc.perform(put("/api/products/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "Laptop Pro",
                                  "description": "Updated laptop",
                                  "price": 2999.90,
                                  "stock": 4
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Laptop Pro"))
                .andExpect(jsonPath("$.stock").value(4));
    }

    @Test
    void updateMissingProductReturnsNotFound() throws Exception {
        mockMvc.perform(put("/api/products/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "Laptop Pro",
                                  "description": "Updated laptop",
                                  "price": 2999.90,
                                  "stock": 4
                                }
                                """))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Product with id 999 was not found"));
    }

    @Test
    void findMissingProductReturnsNotFound() throws Exception {
        mockMvc.perform(get("/api/products/999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Product with id 999 was not found"));
    }

    @Test
    void deleteExistingProductReturnsNoContent() throws Exception {
        mockMvc.perform(delete("/api/products/2"))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/products/2"))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteMissingProductReturnsNotFound() throws Exception {
        mockMvc.perform(delete("/api/products/999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Product with id 999 was not found"));
    }
}

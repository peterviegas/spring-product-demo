package com.viegas.spring_product_demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.viegas.spring_product_demo.dto.ProductDTO;
import com.viegas.spring_product_demo.entity.ProductEntity;
import com.viegas.spring_product_demo.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Collections;

/**
 * Unit tests for {@link ProductController}.
 *
 * <p>This class uses Spring's {@link WebMvcTest} to bootstrap only the web layer and
 * {@link MockBean} to supply a mocked {@link ProductService}.  Each test
 * one endpoint of the REST API and verifies both the HTTP status and response body.
 */

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    private ProductEntity mockPproductEntity;
    private ProductDTO mockProductDTO;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockProductDTO = new ProductDTO(1L, "Product Test", "Description",
                new BigDecimal("19.99"), true);
    }

    @Test
    @DisplayName("GET /api/products should return a list of products")
    void shouldReturnAllProducts() throws Exception {
        when(productService.findAll()).thenReturn(Collections.singletonList(mockProductDTO));

        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("Product Test"))
                .andExpect(jsonPath("$[0].description").value("Description"))
                .andExpect(jsonPath("$[0].price").value("19.99"))
                .andExpect(jsonPath("$[0].available").value(true));
    }

    @Test
    @DisplayName("GET /api/products/{id} should return product when found")
    void shoudReturnById() throws Exception {
        when(productService.findById(1L)).thenReturn(mockProductDTO);

        mockMvc.perform(get("/api/products/{id}",1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Product Test"));
    }

    @Test
    @DisplayName("Post /api/products should created a new product")
    void shouldCreateProduct() throws Exception {
        when(productService.create(ArgumentMatchers.any(ProductDTO.class))).thenReturn(mockProductDTO);

        mockMvc.perform(post("api/products")
                .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mockProductDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Product Test"));
    }

    @Test
    @DisplayName("Put /api/products should update an existing product")
    void shouldUpdateProduct() throws Exception {
        when(productService.update(ArgumentMatchers.any(ProductDTO.class))).thenReturn(mockProductDTO);

        mockMvc.perform(put("api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(mockProductDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Product Test"));
    }

    @Test
    @DisplayName("Del /api/products should return 204 when removed")
    void shouldDeleteProduct() throws Exception {
        mockMvc.perform(delete("/api/products/{1}", 1L))
                .andExpect(status().isNoContent());
    }
}

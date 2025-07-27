package com.viegas.spring_product_demo.service;

import com.viegas.spring_product_demo.dto.ProductDTO;
import com.viegas.spring_product_demo.entity.ProductEntity;
import com.viegas.spring_product_demo.mapper.ProductMapper;
import com.viegas.spring_product_demo.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.never;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Unit tests for {@link ProductService}.
 *
 * <p>These tests focus on the behaviour of the service layer in isolation by mocking
 * the underlying {@link ProductRepository}.  They verify that the service correctly
 * delegates to the repository, applies business rules and uses the {@link com.viegas.spring_product_demo.mapper.ProductMapper}
 * to convert between entities and DTOs.</p>
 */
@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    private ProductService productService;

    private ProductEntity mockProductEntity;
    private ProductDTO mockProductDTO;

    private ProductMapper productMapper;

    @BeforeEach
    void setUp(){
        productService = new ProductService(productRepository);
        mockProductDTO = new ProductDTO(1L, "Product", "Desc", new BigDecimal("10.00"), true);
        mockProductEntity = new ProductEntity(1L, "Product", "Desc", new BigDecimal("10.00"), true);
    }

    @Test
    @DisplayName("getFindAll should map entity for dtos")
    void findGetAll(){
        Mockito.when(productRepository.findAll()).thenReturn(Collections.singletonList(mockProductEntity));

        List<ProductDTO> result = productService.findAll();
        assertEquals(1,result.size());
        assertEquals(mockProductDTO.getId(), result.get(0).getId());
        assertEquals(mockProductDTO.getName(),result.get(0).getName());
    }

    @Test
    @DisplayName("getFindById should return DTO when entity exists")
    void findById(){
        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(mockProductEntity));

        ProductDTO result = productService.findById(1L);
        assertEquals(mockProductDTO.getName(), result.getName());
    }

    @Test
    @DisplayName("create should save and return DTO when ID is null")
    void create(){
        ProductDTO dtoCreate = new ProductDTO(null, "New", "New", new BigDecimal("5.00"), true);
        ProductEntity entityToSave = new ProductEntity(null, "New", "New", new BigDecimal("5.00"), true);
        ProductEntity savedEntity = new ProductEntity(2L, "New", "New", new BigDecimal("5.00"), true);
        Mockito.when(productRepository.existsById(ArgumentMatchers.isNull())).thenReturn(false);
        Mockito.when(productRepository.save(entityToSave)).thenReturn(savedEntity);

        ProductDTO result = productService.create(dtoCreate);
        assertEquals(2L, result.getId());
        assertEquals("New", result.getName());
    }

    @Test
    @DisplayName("update should update and return DTO when ID is not null")
    void update(){
        Mockito.when(productRepository.existsById(mockProductDTO.getId())).thenReturn(true);
        Mockito.when(productRepository.save(mockProductEntity)).thenReturn(mockProductEntity);

        ProductDTO result = productService.update(mockProductDTO);
        assertEquals(mockProductDTO.getId(), result.getId());
        verify(productRepository).save(ArgumentMatchers.any());
    }

    @Test
    @DisplayName("update should throw when entity does not exist or ID is null")
    void updateNotFound(){
        ProductDTO dtoWithNullId = new ProductDTO(null, "Name", "Desc", new BigDecimal("1.00"), true);
        assertThrows(RuntimeException.class, () -> productService.update(dtoWithNullId));
        Mockito.when(productRepository.existsById(1L)).thenReturn(false);
        assertThrows(RuntimeException.class, () -> productService.update(mockProductDTO));
    }

    @Test
    @DisplayName("delete should throw when ID is null or does not exist")
    void delete(){
        Mockito.when(productRepository.existsById(1L)).thenReturn(true);
        doNothing().when(productRepository).deleteById(1L);
        productService.delete(1L);
        verify(productRepository).deleteById(1L);
    }

    @Test
    @DisplayName("delete should throw when ID is null or does not exist")
    void deleteInvalid(){
        assertThrows(RuntimeException.class, () -> productService.delete(null));
        Mockito.when(productRepository.existsById(1L)).thenReturn(false);
        assertThrows(RuntimeException.class, () -> productService.delete(1L));
        verify(productRepository, never()).deleteById(ArgumentMatchers.any());
    }
}

package com.viegas.spring_product_demo.controller;

import com.viegas.spring_product_demo.dto.ProductDTO;
import com.viegas.spring_product_demo.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.util.List;

@RestController
@RequestMapping ("/api/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity <List<ProductDTO>> findAll(){
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity <ProductDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(productService.findById(id));
    }

    @PostMapping
    public ResponseEntity <ProductDTO> create(@RequestBody ProductDTO productDTO){
        productDTO.setId(null);
        return ResponseEntity.ok(productService.create(productDTO));
    }

    @PutMapping
    public ResponseEntity <ProductDTO> update(@RequestBody ProductDTO productDTO){
        return ResponseEntity.ok(productService.update(productDTO));
    }

    @Operation(
            summary = "Delete a product for ID",
            description = "Remove the product of data base if ID is right",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Product deleted with successfully"),
                    @ApiResponse(responseCode = "404", description = "Product not found")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity <ProductDTO> delete(@PathVariable Long id){
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

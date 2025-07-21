package com.viegas.spring_product_demo.service;

import com.viegas.spring_product_demo.dto.ProductDTO;
import com.viegas.spring_product_demo.entity.ProductEntity;
import com.viegas.spring_product_demo.mapper.ProductMapper;
import com.viegas.spring_product_demo.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<ProductDTO> findAll (){
        List<ProductEntity> productEntities = productRepository.findAll();
        return ProductMapper.toDTO(productEntities);
    }

    public ProductDTO findById (Long id){
        ProductEntity productEntity= productRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Product not found with:"));
        return ProductMapper.toDTO(productEntity);
    }

    public ProductDTO create (ProductDTO productDTO){
        if(productDTO.getId() != null && productRepository.existsById(productDTO.getId())){
            throw new RuntimeException("Product with this ID already exist");
        }
        ProductEntity productEntity = productRepository.save(ProductMapper.toEntity(productDTO));
        return ProductMapper.toDTO(productEntity);
    }

    public ProductDTO update (ProductDTO productDTO){
        if(productDTO.getId() == null || !productRepository.existsById(productDTO.getId())){
            throw new RuntimeException("Product not found for actualization");
        }
        productRepository.save(ProductMapper.toEntity(productDTO));
        return productDTO;
    }

    public void delete (Long id){
        if(id == null || !productRepository.existsById(id)){
            throw new RuntimeException("Product not found for delete");
        }
        productRepository.deleteById(id);
    }
}

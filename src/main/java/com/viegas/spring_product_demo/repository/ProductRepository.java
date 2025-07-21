package com.viegas.spring_product_demo.repository;

import com.viegas.spring_product_demo.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
git
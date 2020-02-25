package com.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.portal.entity.Product;


public interface ProductRepository extends JpaRepository<Product, Integer>
{
	@Query( value = "SELECT * FROM product p WHERE p.code = ?", nativeQuery = true)
	Product findProduct(String productCode);

}

package com.ibm.StudyProductMicroservice.Product;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.StudyProductMicroservice.Product.Product;

public interface ProductJPARepository extends JpaRepository<Product, Integer> {

}

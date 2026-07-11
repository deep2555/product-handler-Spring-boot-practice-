package com.deepanshu.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deepanshu.boot.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}

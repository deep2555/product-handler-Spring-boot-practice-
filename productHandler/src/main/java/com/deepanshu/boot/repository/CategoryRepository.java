package com.deepanshu.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deepanshu.boot.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}

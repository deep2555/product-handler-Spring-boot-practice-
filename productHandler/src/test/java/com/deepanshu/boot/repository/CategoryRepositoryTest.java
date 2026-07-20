package com.deepanshu.boot.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.deepanshu.boot.entity.Category;

@DataJpaTest
class CategoryRepositoryTest {

	@Autowired
	private CategoryRepository categoryRepository;
	
	private Category category;
	
	@BeforeEach
	void setUp() throws Exception {
		
		// insert row in category table
		category = new Category();
		category.setCategoryName("test");
		categoryRepository.save(category);
	}

	@AfterEach
	void tearDown() throws Exception {
		// delete row in category table
		categoryRepository.delete(category);
	}


	@Test
	void findByCategoryName() {
		
		Category foundCategory = categoryRepository.findByCategoryName("test").orElse(null);
		assertNotNull(foundCategory);
		assertEquals(category.getCategoryName(), foundCategory.getCategoryName());
	}
	
	@Test
	void deleteByCategoryName() {
		categoryRepository.deleteByCategoryName("test");
		Category foundCategory = categoryRepository.findByCategoryName("test").orElse(null);
		assertNull(foundCategory);
	}
}

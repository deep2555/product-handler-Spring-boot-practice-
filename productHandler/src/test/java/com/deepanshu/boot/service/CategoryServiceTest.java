/**
 * 
 */
package com.deepanshu.boot.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.deepanshu.boot.DTO.CategoryDTO;
import com.deepanshu.boot.entity.Category;
import com.deepanshu.boot.exception.CategoryAlreadyExistsException;
import com.deepanshu.boot.repository.CategoryRepository;

/**
 * 
 */
@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {
	
	
	@Mock
	private CategoryRepository categoryRepository;
	@InjectMocks
	private CategoryService categoryService;
	
	private Category category;
	private CategoryDTO categoryDTO;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		category = new Category();
		categoryDTO = new CategoryDTO();
		category.setId(1L);
		categoryDTO.setId(1L);
		category.setCategoryName("test");
		categoryDTO.setCategoryName("test");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.deepanshu.boot.service.CategoryService#createCategory(com.deepanshu.boot.DTO.CategoryDTO)}.
	 */
	@Test
	void testCreateCategory() {
		when(categoryRepository.findByCategoryName(categoryDTO.getCategoryName())).thenReturn(Optional.empty());
		when(categoryRepository.save(any(Category.class))).thenReturn(category);
		CategoryDTO savedCategory = categoryService.createCategory(categoryDTO);
		assertNotNull(savedCategory);
		assertEquals(categoryDTO.getCategoryName(), savedCategory.getCategoryName());
	}
	
	@Test
	void createCategory_ShouldThrowException_WhenCategoryAlreadyExist() {
		when(categoryRepository.findByCategoryName(categoryDTO.getCategoryName())).thenReturn(Optional.of(category));
		assertThrows(CategoryAlreadyExistsException.class, ()-> categoryService.createCategory(categoryDTO));
		verify(categoryRepository,times(0)).save(any(Category.class));
	}

	/**
	 * Test method for {@link com.deepanshu.boot.service.CategoryService#getAllCategory()}.
	 */
//	@Test
//	void testGetAllCategory() {
//		fail("Not yet implemented");
//	}

	/**
	 * Test method for {@link com.deepanshu.boot.service.CategoryService#getCategoryById(java.lang.Long)}.
	 */
//	@Test
//	void testGetCategoryById() {
//		fail("Not yet implemented");
//	}

	/**
	 * Test method for {@link com.deepanshu.boot.service.CategoryService#deleteCategoryById(long)}.
	 */
//	@Test
//	void testDeleteCategoryById() {
//		fail("Not yet implemented");
//	}

}

package com.deepanshu.boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deepanshu.boot.DTO.CategoryDTO;
import com.deepanshu.boot.service.CategoryService;

import io.swagger.v3.oas.annotations.tags.Tag;
@Tag(
		name = "Category Rest Api Curd Operation",
		description = "Create, get, update, Delete operation"
		)
@RestController
@RequestMapping("/api/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	

	// create category
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@PostMapping("/createCategory")
	public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO) {
		return new ResponseEntity<>(categoryService.createCategory(categoryDTO), HttpStatus.CREATED);
	}

	// get category
	@GetMapping("/getAllCategory")
	public List<CategoryDTO> getAllCategory() {
		return categoryService.getAllCategory();

	}
	
	// get category by id
	
	@GetMapping("/getCategoryById/{id}")
	public CategoryDTO getCategoryById(@PathVariable Long id) {
		return categoryService.getCategoryById(id);
	}
	
	// delete category
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@DeleteMapping("/deleteCategory/{id}")
	public String deleteCategory(@PathVariable Long id) {
		return categoryService.deleteCategoryById(id);
	}
	
}

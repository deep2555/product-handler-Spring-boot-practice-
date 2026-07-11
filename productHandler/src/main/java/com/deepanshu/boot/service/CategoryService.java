package com.deepanshu.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deepanshu.boot.DTO.CategoryDTO;
import com.deepanshu.boot.entity.Category;
import com.deepanshu.boot.mapper.CategoryMapper;
import com.deepanshu.boot.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	// create category
	public CategoryDTO createCategory(CategoryDTO categoryDTO) {
		Category dtoTOEntity = CategoryMapper.dtoTOEntity(categoryDTO);
		dtoTOEntity = categoryRepository.save(dtoTOEntity);
		return CategoryMapper.entityTODto(dtoTOEntity);

	}

	// get all category
	public List<CategoryDTO> getAllCategory() {
		List<Category> allCategories = categoryRepository.findAll();
		return allCategories.stream().map(CategoryMapper::entityTODto).toList();
	}

	// get category by id
	public CategoryDTO getCategoryById(Long id) {

		Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("category not found"));

		return CategoryMapper.entityTODto(category);
	}

	// delete category

	public String deleteCategoryById(long id) {
		categoryRepository.deleteById(id);
		return "category" + id + "is deleted";
	}

}

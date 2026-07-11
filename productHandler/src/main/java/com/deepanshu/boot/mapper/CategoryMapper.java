package com.deepanshu.boot.mapper;

import com.deepanshu.boot.DTO.CategoryDTO;
import com.deepanshu.boot.entity.Category;

public class CategoryMapper {

	// convert dto to entity
	public static Category dtoTOEntity(CategoryDTO categoryDTO) {
		Category category = new Category();
		category.setCategoryName(categoryDTO.getCategoryName());
		return category;
	}

	// form entity to dto
	public static CategoryDTO entityTODto(Category category) {
		if (category == null) {
			return null; // later handel with exception
		}
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setId(category.getId());
		categoryDTO.setCategoryName(category.getCategoryName());
		categoryDTO.setProductsList(category.getProducts().stream().map(ProductMapper::entityToDto).toList());

		return categoryDTO;
	}
}

package com.deepanshu.boot.service;

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
}

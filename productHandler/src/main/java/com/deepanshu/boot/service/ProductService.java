package com.deepanshu.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deepanshu.boot.DTO.ProductDTO;
import com.deepanshu.boot.entity.Category;
import com.deepanshu.boot.entity.Product;
import com.deepanshu.boot.mapper.ProductMapper;
import com.deepanshu.boot.repository.CategoryRepository;
import com.deepanshu.boot.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	// create product
	public ProductDTO createProduct(ProductDTO productDto) {
		// check the category present in database
		Category category = categoryRepository.findById(productDto.getCategoryId())
				.orElseThrow(() -> new RuntimeException("category not found"));
		// mapper from dto to entity
		Product dtoToEntity = ProductMapper.dtoToEntity(productDto, category);
		dtoToEntity = productRepository.save(dtoToEntity);
		// mapper from entity to dto
		return ProductMapper.entityToDto(dtoToEntity);
	}

	// fetch all product
	public List<ProductDTO> getAllProduct() {
		return productRepository.findAll().stream().map(ProductMapper::entityToDto).toList();
	}

	// fetch product by id
	public ProductDTO getProductById(Long id) {
		Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("product not found!"));
		return ProductMapper.entityToDto(product);
	}

	// update product by category
	public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
		Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("product not found!"));

		Category category = categoryRepository.findById(productDTO.getCategoryId())
				.orElseThrow(() -> new RuntimeException("category not found"));

		product.setProductName(productDTO.getProductName());
		product.setProductDescription(productDTO.getProductDescription());
		product.setProductPrice(productDTO.getProductPrice());
		product.setCategory(category);

		product = productRepository.save(product);
		return ProductMapper.entityToDto(product);
	}

}

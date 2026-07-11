package com.deepanshu.boot.mapper;

import com.deepanshu.boot.DTO.ProductDTO;
import com.deepanshu.boot.entity.Category;
import com.deepanshu.boot.entity.Product;

public class ProductMapper {

	
	// entity to dto 
	public static ProductDTO entityToDto(Product product) {
		return new ProductDTO(
				product.getId(),
				product.getProductName(),
				product.getProductDescription(),
				product.getProductPrice(),
				product.getCategory().getId()
				);
	}
	// dto to entity
	public static Product dtoToEntity(ProductDTO productDTO, Category category) {
		Product product = new Product();
		product.setProductName(productDTO.getProductName());
		product.setProductDescription(productDTO.getProductDescription());
		product.setProductPrice(productDTO.getProductPrice());
		product.setCategory(category);
		
		return product;
	}
}

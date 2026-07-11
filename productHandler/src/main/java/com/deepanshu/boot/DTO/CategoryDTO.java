package com.deepanshu.boot.DTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {

	
	private Long id;
	private String categoryName;
	private List<ProductDTO> productsList;
}

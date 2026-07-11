package com.deepanshu.boot.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

	private Long id;
	private String productName;
	private String productDescription;
	private Double productPrice;
	private Long categoryId;
}

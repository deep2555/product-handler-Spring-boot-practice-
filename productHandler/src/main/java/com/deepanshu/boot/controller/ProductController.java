package com.deepanshu.boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deepanshu.boot.DTO.ProductDTO;
import com.deepanshu.boot.service.ProductService;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(
		name = "Product Rest Api Curd Operation",
		description = "Create, get, update, Delete operation"
		)

@RestController
@RequestMapping("/api/product")
public class ProductController {

	@Autowired
	private ProductService productService;


	@PreAuthorize("hasAuthority('ROLE_SELLER')")
	@PostMapping("/createProduct")
	public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
		return new ResponseEntity<>(productService.createProduct(productDTO), HttpStatus.CREATED);
	}

	// get all product
	@GetMapping("/getAllProduct")
	public List<ProductDTO> getAllProduct(@RequestBody ProductDTO productDTO) {
		return productService.getAllProduct();

	}
	
	// get product by id
	@GetMapping("/getProductById/{id}")
	public ProductDTO getProductById(@PathVariable Long id) {
		 return productService.getProductById(id);
	}
	
	// update product 
	@PreAuthorize("hasAuthority('ROLE_SELLER')")
	@PutMapping("/updateProduct/{id}")
	public ProductDTO updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
		return productService.updateProduct(id, productDTO);
	}

}

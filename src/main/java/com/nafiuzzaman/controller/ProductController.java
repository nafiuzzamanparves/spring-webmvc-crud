package com.nafiuzzaman.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.nafiuzzaman.model.Product;
import com.nafiuzzaman.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping
	public List<Product> getAllProducts() {
		return productService.getAllProducts();
	}

	@GetMapping("/{id}")
	public Product getProductById(@PathVariable Long id) {
		return productService.getProductById(id);
	}

	@PostMapping
	public void createProduct(@RequestBody Product product) {
		productService.saveProduct(product);
	}

	@PutMapping("/{id}")
	public void updateProduct(@PathVariable Long id, @RequestBody Product product) {
		product.setId(id);
		productService.updateProduct(product);
	}

	@DeleteMapping("/{id}")
	public void deleteProduct(@PathVariable Long id) {
		productService.deleteProduct(id);
	}
}
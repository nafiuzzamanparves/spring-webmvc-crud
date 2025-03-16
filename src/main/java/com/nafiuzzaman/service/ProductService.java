package com.nafiuzzaman.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nafiuzzaman.model.Product;
import com.nafiuzzaman.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	public Product getProductById(Long id) {
		return productRepository.findById(id);
	}

	public void saveProduct(Product product) {
		productRepository.save(product);
	}

	public void updateProduct(Product product) {
		productRepository.update(product);
	}

	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}
}
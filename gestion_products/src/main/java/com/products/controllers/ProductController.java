package com.products.controllers;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.products.dtos.ProductDto;
import com.products.requests.ProductRequest;
import com.products.responses.ProductResponse;
import com.products.services.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	@Autowired
	ProductService productService;

	@GetMapping("/all")
	public List<ProductResponse> getProducts() {
		
		ModelMapper modelMapper = new ModelMapper();
		
		List<ProductResponse> productsResponses = new ArrayList<>();
		
		List<ProductDto> listProducts = productService.getProducts();
		
		for(ProductDto product: listProducts) {
			
			ProductResponse products = modelMapper.map(product, ProductResponse.class);
			
			productsResponses.add(products);
		}
		
		return productsResponses;
		
	}
	
	@PostMapping("/add")
	public ResponseEntity<ProductResponse> addProduct(@RequestBody ProductRequest productRequest) {
		
		ModelMapper modelMapper = new ModelMapper();
		
		ProductDto productDto = modelMapper.map(productRequest, ProductDto.class);
		
		ProductDto newProduct = productService.createProduct(productDto);
		
		ProductResponse productResponse = modelMapper.map(newProduct, ProductResponse.class);
		
		return new ResponseEntity<ProductResponse>(productResponse, HttpStatus.CREATED);
	}
	
	@PutMapping("/edit/{id}")
	public ResponseEntity<ProductResponse> editProduct(@PathVariable String id, @RequestBody ProductRequest productRequest) {
		
		ModelMapper modelMapper = new ModelMapper();
		
		ProductDto productDto = modelMapper.map(productRequest, ProductDto.class);
		
		ProductDto newProduct = productService.updateProduct(id, productDto);
		
		ProductResponse productResponse = modelMapper.map(newProduct, ProductResponse.class);
		
		return new ResponseEntity<ProductResponse>(productResponse, HttpStatus.ACCEPTED);
	}
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteProduct(@PathVariable String id) {
		
		productService.deleteProduct(id);
		
    	return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}
	
	
	@GetMapping("/pagination")
	public List<ProductResponse> getAll(@RequestParam(value = "page" , defaultValue = "1") int page, @RequestParam(value = "limit", defaultValue = "5") int limit) {
		
		ModelMapper modelMapper = new ModelMapper();
		
		List<ProductResponse> productsResponse = new ArrayList<>();
		
		List<ProductDto> productsDto = productService.getAll(page, limit);
		
		for(ProductDto product: productsDto) {
			
			ProductResponse productResponse = modelMapper.map(product, ProductResponse.class);
			productsResponse.add(productResponse);
		}
		
		return productsResponse;
		
	}
	
  	@GetMapping("/search") 
	public List<ProductResponse> searchProduct() {

  		
  		ModelMapper modelMaper = new ModelMapper();
		List<ProductResponse> productsResponse = new ArrayList<>();
		
		List<ProductDto> productsDto = productService.search();
		
		for(ProductDto product: productsDto) {
			
			ProductResponse productResponse = modelMaper.map(product, ProductResponse.class);
			
			productsResponse.add(productResponse);
		}

  		return productsResponse;
  	}
	
}

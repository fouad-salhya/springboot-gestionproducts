package com.products.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import com.products.entities.ProductEntity;
import com.products.entities.Type;
import com.products.requests.ProductRequest;
import com.products.responses.ProductResponse;
import com.products.services.ProductService;

@Controller
@RequestMapping("/restoran")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@GetMapping("/test")
	
	public List<ProductEntity> test() {
		
  		List<ProductEntity> products = productService.getProducts();
  		List<ProductEntity> productsBreakfast = products.stream().filter(product -> product.getType().equals(Type.dinner)).collect(Collectors.toList());

  		 return productsBreakfast;
		
	}
	
	@GetMapping("/home")
	public String getIndex(Model model) {
		
  		List<ProductEntity> products = productService.getProducts();
  		
  		List<ProductEntity> productsBreakfast = products.stream().filter(product -> product.getType().equals(Type.breakfast)).collect(Collectors.toList());
  		List<ProductEntity> productsDinner = products.stream().filter(product -> product.getType().equals(Type.dinner)).collect(Collectors.toList());
  		List<ProductEntity> productsLaunch = products.stream().filter(product -> product.getType().equals(Type.launch)).collect(Collectors.toList());

		
		model.addAttribute("types", Type.values());
		model.addAttribute("productsBreakfast", productsBreakfast);
		model.addAttribute("productsDinner", productsDinner);
		model.addAttribute("productsLaunch", productsLaunch);

		
		return "index";
	}
	
	
	@GetMapping("/about")
	public String getAbout() {
		return "about";
	}
	
	@GetMapping("/booking")
	public String getBooking() {
		return "booking";
	}
	
	@GetMapping("/contact")
	public String getContact() {
		return "contact";
	}
	
	@GetMapping("/menu")
	public String getMenu() {
		return "menu";
	}
	
	@GetMapping("/service")
	public String getService() {
		return "service";
	}
	
	@GetMapping("/team")
	public String getTeam() {
		return "team";
	}
	
	@GetMapping("/testimonial")
	public String getTestimonial() {
		return "testimonial";
	}

	
	/*
	@PostMapping("/add")
	public ResponseEntity<ProductResponse> addProduct(@RequestBody ProductRequest productRequest) {
		
		ModelMapper modelMapper = new ModelMapper();
		
		ProductDto productDto = modelMapper.map(productRequest, ProductDto.class);
		
		ProductDto newProduct = productService.createProduct(productDto);
		
		ProductResponse productResponse = modelMapper.map(newProduct, ProductResponse.class);
		
		return new ResponseEntity<ProductResponse>(productResponse, HttpStatus.CREATED);
	}*/
	
	@PostMapping("/add")
	public ProductDto addProduct(@RequestBody ProductRequest productRequest) {
		
        ModelMapper modelMapper = new ModelMapper();
		
		ProductDto productDto = modelMapper.map(productRequest, ProductDto.class);
		ProductDto product = productService.createProduct(productDto);
		
		return product;
		
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

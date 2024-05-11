package com.products.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.products.dtos.ProductDto;
import com.products.entities.ProductEntity;
import com.products.entities.ReservationEntity;
import com.products.entities.Type;
import com.products.requests.ProductRequest;
import com.products.responses.ProductResponse;
import com.products.services.ProductService;

@Controller
@RequestMapping("/restoran")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@GetMapping("/home")
	public String getIndex(Model model) {
		
  		List<ProductEntity> products = productService.getProducts();
  		
  		List<ProductEntity> productsBreakfast = products.stream().filter(product -> product.getType().equals(Type.breakfast)).collect(Collectors.toList());
  		List<ProductEntity> productsDinner = products.stream().filter(product -> product.getType().equals(Type.dinner)).collect(Collectors.toList());
  		List<ProductEntity> productsLaunch = products.stream().filter(product -> product.getType().equals(Type.launch)).collect(Collectors.toList());

		
		model.addAttribute("types", Type.values());
		model.addAttribute("reservation", new ReservationEntity());
		model.addAttribute("productsBreakfast", productsBreakfast);
		model.addAttribute("productsDinner", productsDinner);
		model.addAttribute("productsLaunch", productsLaunch);

		
		return "index";
	}
	
	@GetMapping("/product/detail/{id}")
	public String detailProduct(@PathVariable long id, Model model) {
		
		Optional<ProductEntity> productOptional = productService.getOneProduct(id);
		
		ProductEntity product = productOptional.get();
		
		List<ProductEntity> relatedProducts = productService.getRelatedProducts(product.getType());
		
		model.addAttribute("product", product);
		
		model.addAttribute("relatedProducts", relatedProducts);
		
		return "product_detail";
	}
	
	@GetMapping("/commande/payment/{id}")
	public String getCart(@PathVariable long id, Model model) {
		
        Optional<ProductEntity> productOptional = productService.getOneProduct(id);
		
		ProductEntity product = productOptional.get();
		model.addAttribute("product", product);

		int quantity = 1;
		model.addAttribute("quantity", quantity);
		
		return "cart.html";
	}
	
	
	@PostMapping("/home")
	public String reserverTable(ReservationEntity reservation, Model model) {
		
		productService.reserverTable(reservation);
		
		String message = "votre reservation fait avec success check your inbox ";
		
		model.addAttribute("message", message);
		
		return "redirect:/home";
		
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

	
	@PostMapping("/add")
	public ProductDto addProduct(@RequestBody ProductRequest productRequest) {
		
        ModelMapper modelMapper = new ModelMapper();
		
		ProductDto productDto = modelMapper.map(productRequest, ProductDto.class);
		ProductDto product = productService.createProduct(productDto);
		
		return product;
		
	}
	
	
	
	
	
	
	
}

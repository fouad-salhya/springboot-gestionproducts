package com.products.controllers;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.products.entities.CommandeEntity;
import com.products.requests.PaymentRequest;
import com.products.services.CommandeService;
import com.products.services.PaymentService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@RestController
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	PaymentService paymentService;
	
	@Autowired
	CommandeService commandeService;
	
	@GetMapping("/cart")
	public String cart(Model model) {
		
		String token = paymentService.generateToken();
		
        model.addAttribute("token", token);
        
		return "cart";
	}
	

	
	@PostMapping("/process-payment/{id}")
    @ResponseBody
    public String processPayment(@RequestBody PaymentRequest paymentRequest, @PathVariable long id) {
        String nonce = paymentRequest.getNonce();
        BigDecimal amount = paymentRequest.getAmount();
        String result = paymentService.processPayment(nonce, amount);
        
        return result;
	}
	
	
}

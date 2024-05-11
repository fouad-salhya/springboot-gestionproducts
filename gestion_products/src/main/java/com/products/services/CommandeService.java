package com.products.services;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.products.entities.CommandeEntity;
import com.products.entities.ProductEntity;
import com.products.repository.CommandeRepository;
import com.products.repository.ProductRepository;
import com.products.shared.Utils;

@Service
public class CommandeService {
	
	@Autowired
	CommandeRepository commandeRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	Utils utils;

	public CommandeEntity addCommande(String numCommande,BigDecimal amount,long id) {
		
		Optional<ProductEntity> productEntity = productRepository.findById(id);
		
		CommandeEntity commandeEntity = new CommandeEntity();
		
		commandeEntity.setCommandeId(utils.generateStringId(32));
		
		commandeEntity.setNumCommande(numCommande);
		
		commandeEntity.setPrix_total(amount);
				
		commandeEntity.setProductName(productEntity.get().getName());
		
		
		CommandeEntity commande = commandeRepository.save(null);
		
		return commande;
		
	
	}
	
}

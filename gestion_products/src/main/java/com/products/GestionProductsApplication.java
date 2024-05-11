package com.products;

import java.awt.Window.Type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.products.entities.Role;
import com.products.entities.UserEntity;
import com.products.repository.UserRepository;
import com.products.shared.Utils;

@SpringBootApplication
public class GestionProductsApplication implements CommandLineRunner{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Utils utils;

	public static void main(String[] args) {
		SpringApplication.run(GestionProductsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	
				
		/*
		userRepository.save(new UserEntity(utils.generateStringId(32), "fouad", "fouad@email.com", "test", "text", Role.User));
		*/
	}

}

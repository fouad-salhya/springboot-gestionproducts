package com.products.services;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.ClientTokenRequest;
import com.braintreegateway.Environment;
import com.braintreegateway.Result;
import com.braintreegateway.Transaction;
import com.braintreegateway.TransactionRequest;
import com.products.entities.UserEntity;
import com.products.repository.UserRepository;


@Service
public class PaymentService {
	
	@Autowired
	UserRepository userRespository;
	

	private static BraintreeGateway gateway = new BraintreeGateway(
			  Environment.SANDBOX,
			  "fr8ww8xycwk23y7t",
			  "f3tqsn9y7mzvnzt3",
			  "8e03f71fb87e4fa227368ca82e2db55e"
			);
	
	public String generateToken() {
		
			ClientTokenRequest clientTokenRequest = new ClientTokenRequest();
			  
			String clientToken = gateway.clientToken().generate(clientTokenRequest);
			
			return clientToken;
		
		
	}
	
	 public String processPayment(String nonce, BigDecimal amount) {
	        TransactionRequest request = new TransactionRequest()
	                .amount(amount)
	                .paymentMethodNonce(nonce)
	                .options()
	                .submitForSettlement(true)
	                .done();

	        Result<Transaction> result = gateway.transaction().sale(request);

	        if (result.isSuccess()) {
	            return result.getTarget().getId();
	        } else {
	            return "Erreur de paiement: " + result.getMessage();
	        }
	
	 }
}



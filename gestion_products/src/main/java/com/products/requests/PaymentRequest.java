package com.products.requests;

import java.math.BigDecimal;

public class PaymentRequest {
	
	private String nonce;
	
	private BigDecimal amount;
	
	private int quantityNumber ;

	public int getQuantity() {
		return quantityNumber;
	}

	public void setQuantity(int quantity) {
		this.quantityNumber = quantity;
	}

	public String getNonce() {
		return nonce;
	}

	public void setNonce(String nonce) {
		this.nonce = nonce;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

}

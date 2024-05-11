package com.products.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "commande")
public class CommandeEntity implements Serializable{

	private static final long serialVersionUID = 672446254794008801L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column
	private String commandeId;
	
	@Column 
	private String productName;
	
	@Column 
	private String numCommande;
	
	@Column
	private int quantity;
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCommandeId() {
		return commandeId;
	}

	public void setCommandeId(String commandeId) {
		this.commandeId = commandeId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getNumCommande() {
		return numCommande;
	}

	public void setNumCommande(String numCommande) {
		this.numCommande = numCommande;
	}

	public BigDecimal getPrix_total() {
		return prix_total;
	}

	public void setPrix_total(BigDecimal prix_total) {
		this.prix_total = prix_total;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public List<ProductEntity> getProducts() {
		return products;
	}

	public void setProducts(List<ProductEntity> products) {
		this.products = products;
	}

	@Column
	private BigDecimal prix_total;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private UserEntity user;

	@ManyToMany
    @JoinTable(
        name = "commande_produit",
        joinColumns =  {@JoinColumn(name = "commande_id")},
        inverseJoinColumns = { @JoinColumn(name = "product_id")})
	private List<ProductEntity> products;


}

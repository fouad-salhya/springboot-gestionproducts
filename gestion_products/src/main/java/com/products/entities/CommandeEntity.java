package com.products.entities;

import java.io.Serializable;
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
	
	@DateTimeFormat(pattern = "MM/dd/yyyy HH:mm")
    private Date date_commande;
	
	@Column
	private double prix_total;
	
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

package tn.esprit.spring.Model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.Model.Produit.Produit;
@Entity
public class LigneCommande implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private int quantity;
	
	private double price;
	
	private String status;
	
	@ManyToOne
	private Produit produit;
	 
	@ManyToOne(cascade = CascadeType.PERSIST)
    private Commande commande ;
	 
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public Produit getProduit() {
		return produit;
	}
	
	public void setProduit(Produit produit) {
		this.produit = produit;
	}
	
	public Commande getCommande() {
		return commande;
	}
	
	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	public LigneCommande( int quantity) {
		super();
		
		this.quantity = quantity;
	}

	public LigneCommande() {
		super();
	}
	
	
	

}

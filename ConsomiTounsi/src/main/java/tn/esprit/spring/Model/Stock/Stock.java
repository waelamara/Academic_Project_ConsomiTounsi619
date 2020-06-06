package tn.esprit.spring.Model.Stock;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import tn.esprit.spring.Model.Produit.Produit;
@Entity
public class Stock implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long idstock;
	private String nom_stock;
	private int quantite;
	@Temporal (TemporalType.DATE)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date validite;
	private float remise;
	private float prixdevente;
	private float prixremise;
    @ManyToOne
	private Produit idProduit;
    
    

	public Date getValidite() {
		return validite;
	}

	public void setValidite(Date validite) {
		this.validite = validite;
	}

	public float getRemise() {
		return remise;
	}

	public void setRemise(float remise) {
		this.remise = remise;
	}

	public float getPrixdevente() {
		return prixdevente;
	}

	public void setPrixdevente(float prixdevente) {
		this.prixdevente = prixdevente;
	}

	public float getPrixremise() {
		return prixremise;
	}

	public void setPrixremise(float prixremise) {
		this.prixremise = prixremise;
	}

	public Long getIdstock() {
		return idstock;
	}

	public void setIdstock(Long idstock) {
		this.idstock = idstock;
	}

	public String getNom_stock() {
		return nom_stock;
	}

	public void setNom_stock(String nom_stock) {
		this.nom_stock = nom_stock;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}



	public Produit getIdProduit() {
		return idProduit;
	}

	public void setIdProduit(Produit idProduit) {
		this.idProduit = idProduit;
	}

	public Stock() {
		super();
	}

	public Stock(Long idstock, String nom_stock, int quantite) {
		super();
		this.idstock = idstock;
		this.nom_stock = nom_stock;
		this.quantite = quantite;
	}

	public Stock( String nom_stock, int quantite, Date validite, float prixdevente, Produit idProduit) {
		super();
		
		this.nom_stock = nom_stock;
		this.quantite = quantite;
		this.validite = validite;
		this.prixdevente = prixdevente;
		this.idProduit = idProduit;
	}

	public Stock(String nom_stock, int quantite, Date validite, float prixdevente) {
		super();
		this.nom_stock = nom_stock;
		this.quantite = quantite;
		this.validite = validite;
		this.prixdevente = prixdevente;
	}

	

	
	
	
	
	
	
	
	
	
}
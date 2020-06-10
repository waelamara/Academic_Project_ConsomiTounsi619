package tn.esprit.spring.Model.Produit;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import tn.esprit.spring.Model.LigneCommande;
import tn.esprit.spring.Model.Rayon.Rayon;
import tn.esprit.spring.Model.Stock.Stock;





@Entity
@JsonIgnoreProperties
public class Produit implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nomProduit;
	private float prix;
	private String description;
	private Long barcode;
	private float poids;
	private float prixAchat;
	@JsonIgnore
	@ManyToOne
	private SsCategorie idSsCategorie;
	
	@JsonIgnore
	@OneToMany(mappedBy="Idproduit",cascade=CascadeType.ALL)
	private Set<ImageProduit> Images;
	@OneToMany(mappedBy="produit")
	@JsonIgnore
	private Set<LigneCommande> ligneCommande;
	@ManyToOne(cascade=CascadeType.ALL)
	Rayon Idrayon;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="idProduit")
	private Set<Stock> Stocks;

	



	
	public Set<Stock> getStocks() {
		return Stocks;
	}

	public void setStocks(Set<Stock> stocks) {
		Stocks = stocks;
	}

	public Produit() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomProduit() {
		return nomProduit;
	}

	public void setNomProduit(String nomProduit) {
		this.nomProduit = nomProduit;
	}

	public float getPrix() {
		return prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	

	public Long getBarcode() {
		return barcode;
	}

	public void setBarcode(Long barcode) {
		this.barcode = barcode;
	}

	public float getPoids() {
		return poids;
	}

	public void setPoids(float poids) {
		this.poids = poids;
	}

	public float getPrixAchat() {
		return prixAchat;
	}

	public void setPrixAchat(float prixAchat) {
		this.prixAchat = prixAchat;
	}

	public SsCategorie getIdSsCategorie() {
		return idSsCategorie;
	}

	public void setIdSsCategorie(SsCategorie idSsCategorie) {
		this.idSsCategorie = idSsCategorie;
	}


	public void setImages(Set<ImageProduit> images) {
		Images = images;
	}
	
	

	public Set<ImageProduit> getImages() {
		return Images;
	}

	public Rayon getIdrayon() {
		return Idrayon;
	}

	public void setIdrayon(Rayon idrayon) {
		Idrayon = idrayon;
	}
	



	public Produit(Long id, String nomProduit, float prix, String description, Long barcode, float poids,
			float prixAchat) {
		super();
		this.id = id;
		this.nomProduit = nomProduit;
		this.prix = prix;
		this.description = description;
		this.barcode = barcode;
		this.poids = poids;
		this.prixAchat = prixAchat;
	}

	public Produit(String nomProduit, float prix, String description, Long barcode, float poids, float prixAchat) {
		super();
		this.nomProduit = nomProduit;
		this.prix = prix;
		this.description = description;
		this.barcode = barcode;
		this.poids = poids;
		this.prixAchat = prixAchat;
	}

	public Produit(String nomProduit, float prix, String description, Long barcode, float poids, float prixAchat,
			SsCategorie idSsCategorie) {
		super();
		this.nomProduit = nomProduit;
		this.prix = prix;
		this.description = description;
		this.barcode = barcode;
		this.poids = poids;
		this.prixAchat = prixAchat;
		this.idSsCategorie = idSsCategorie;
	}

	public Produit(String nomProduit, float prix, String description, Long barcode, float poids, float prixAchat,
			Set<ImageProduit> images) {
		super();
		this.nomProduit = nomProduit;
		this.prix = prix;
		this.description = description;
		this.barcode = barcode;
		this.poids = poids;
		this.prixAchat = prixAchat;
		this.Images = images;
	}



	public Boolean BarcodeIsvalid(long barcode){
		long c =10000000000L;
		long b=barcode/c;
		if(b==619){
			return true;
		}
		return false;
	}
	

}

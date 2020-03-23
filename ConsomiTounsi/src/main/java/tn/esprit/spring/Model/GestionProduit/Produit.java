package tn.esprit.spring.Model.GestionProduit;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import tn.esprit.spring.Model.LigneCommande;
import tn.esprit.spring.Model.Rayon;
import tn.esprit.spring.Model.Stock;





@Entity
public class Produit implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	private String NomProduit;
	private float Prix;
	private String Description;
	private int Barcode;
	private float Poids;
	private float PrixAchat;
	@ManyToOne
	private SsCategorie IdSsCategorie;
	
	
	@OneToMany(mappedBy="Idproduit",cascade=CascadeType.ALL)
	private Set<ImageProduit> Images;
	@OneToMany(mappedBy="produit")
	@JsonIgnore
	private Set<LigneCommande> ligneCommande;
	@ManyToOne
	Rayon Idrayon;
	@OneToOne
	private Stock IdStock;



	public Produit() {

	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getNomProduit() {
		return NomProduit;
	}

	public void setNomProduit(String nomProduit) {
		NomProduit = nomProduit;
	}

	public float getPrix() {
		return Prix;
	}

	public void setPrix(float prix) {
		Prix = prix;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public int getBarcode() {
		return Barcode;
	}

	public void setBarcode(int barcode) {
		Barcode = barcode;
	}

	public float getPoids() {
		return Poids;
	}

	public void setPoids(float poids) {
		Poids = poids;
	}

	public float getPrixAchat() {
		return PrixAchat;
	}

	public void setPrixAchat(float prixAchat) {
		PrixAchat = prixAchat;
	}

	public SsCategorie getIdSsCategorie() {
		return IdSsCategorie;
	}

	public void setIdSsCategorie(SsCategorie idSsCategorie) {
		IdSsCategorie = idSsCategorie;
	}

	public Set<ImageProduit> getImages() {
		return Images;
	}

	public void setImages(Set<ImageProduit> images) {
		Images = images;
	}

	public Produit(String nomProduit, float prix, String description, int barcode, float poids, float prixAchat,
			SsCategorie idSsCategorie) {
		super();
		NomProduit = nomProduit;
		Prix = prix;
		Description = description;
		Barcode = barcode;
		Poids = poids;
		PrixAchat = prixAchat;
		IdSsCategorie = idSsCategorie;
	}

	
	
	

}

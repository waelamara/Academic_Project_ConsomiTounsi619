package tn.esprit.spring.Model.Produit;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties
public class ImageProduit implements Serializable {
	/*
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String image;
	@ManyToOne
	@JsonIgnore
	private Produit Idproduit;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Produit getIdproduit() {
		return Idproduit;
	}

	public void setIdproduit(Produit idproduit) {
		Idproduit = idproduit;
	}

	public ImageProduit() {
	}

	
	public ImageProduit(String image) {
		super();
		this.image = image;
	}

	public ImageProduit(String image, Produit idproduit) {
		super();
		this.image = image;
		Idproduit = idproduit;
	}



	
	
	
}

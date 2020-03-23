package tn.esprit.spring.Model.GestionProduit;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ImageProduit implements Serializable {
	/*
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	private String Image;
	@ManyToOne
	private Produit Idproduit;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getImage() {
		return Image;
	}

	public void setImage(String image) {
		Image = image;
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
		Image = image;
	}

	public ImageProduit(String image, Produit idproduit) {
		super();
		Image = image;
		Idproduit = idproduit;
	}



	
	
	
}

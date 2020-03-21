package tn.esprit.spring.Model.GestionProduit;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Image implements Serializable {
	/*
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	private float NomImage;
	@ManyToOne
	private Produit Idproduit;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public float getNomImage() {
		return NomImage;
	}

	public void setNomImage(float nomImage) {
		NomImage = nomImage;
	}

	public Produit getIdproduit() {
		return Idproduit;
	}

	public void setIdproduit(Produit idproduit) {
		Idproduit = idproduit;
	}

	public Image() {
	}

}

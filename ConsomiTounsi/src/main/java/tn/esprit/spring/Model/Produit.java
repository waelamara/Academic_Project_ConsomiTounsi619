package tn.esprit.spring.Model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;





@Entity
public class Produit implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long Id;
	public String NomProduit;
	public float Prix;
	public String Description;
	public int Barcode;
	public float Poids;
	public float PrixAchat;
	
	@ManyToOne
	public SsCategorie IdSsCategorie;
	@OneToMany(mappedBy="Idproduit")
	public Set<Image> Images;
	
	
	
	

	public Produit() {

	}
	
	

}

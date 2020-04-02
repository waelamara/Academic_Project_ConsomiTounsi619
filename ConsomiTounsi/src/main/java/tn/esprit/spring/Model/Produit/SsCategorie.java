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

@Entity
@JsonIgnoreProperties
public class SsCategorie implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	private String NomSsCategorie;
	@OneToMany(mappedBy = "IdSsCategorie",cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Produit> Produits;
	@ManyToOne
	@JsonIgnore
	private SCategorie IdSCategorie;


	public SsCategorie() {
	}
	

	public SsCategorie(String nomSsCategorie, SCategorie idSCategorie) {
		super();
		NomSsCategorie = nomSsCategorie;
		IdSCategorie = idSCategorie;
	}


	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getNomSsCategorie() {
		return NomSsCategorie;
	}

	public void setNomSsCategorie(String nomSsCategorie) {
		NomSsCategorie = nomSsCategorie;
	}



	public void setProduits(Set<Produit> produits) {
		Produits = produits;
	}

	public SCategorie getIdSCategorie() {
		return IdSCategorie;
	}

	public void setIdSCategorie(SCategorie idSCategorie) {
		IdSCategorie = idSCategorie;
	}


	public Set<Produit> getProduits() {
		return Produits;
	}

}

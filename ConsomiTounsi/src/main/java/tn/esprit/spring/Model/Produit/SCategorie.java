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
public class SCategorie implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	private String NomSCategorie;
	@ManyToOne(cascade = CascadeType.REMOVE)
	@JsonIgnore
	private Categorie IdCategorie;
	@OneToMany(mappedBy="IdSCategorie",cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<SsCategorie> SsCategories;
	
	public SCategorie() {
	}

	
	public SCategorie(String nomSCategorie, Categorie idCategorie) {
		super();
		NomSCategorie = nomSCategorie;
		IdCategorie = idCategorie;
	}


	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getNomSCategorie() {
		return NomSCategorie;
	}

	public void setNomSCategorie(String nomSCategorie) {
		NomSCategorie = nomSCategorie;
	}

	public Categorie getIdCategorie() {
		return IdCategorie;
	}

	public void setIdCategorie(Categorie idCategorie) {
		IdCategorie = idCategorie;
	}



	public void setSsCategories(Set<SsCategorie> ssCategories) {
		SsCategories = ssCategories;
	}
	
	
}

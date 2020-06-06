package tn.esprit.spring.Model.Produit;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties
public class Categorie implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nomCategorie;
	
	@OneToMany(mappedBy = "idCategorie",cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<SCategorie> Categories;

	public Categorie() {
		super();
	}
	

	public Categorie(String nomCategorie) {
		super();
		this.nomCategorie = nomCategorie;
	}


	public Categorie(Long id, String nomCategorie) {
		super();
		this.id = id;
		this.nomCategorie = nomCategorie;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getNomCategorie() {
		return nomCategorie;
	}

	public void setNomCategorie(String nomCategorie) {
		this.nomCategorie = nomCategorie;
	}



	public void setCategories(Set<SCategorie> categories) {
		Categories = categories;
	}

	public Set<SCategorie> getCategories() {
		return Categories;
	}



}

package tn.esprit.spring.Model.GestionProduit;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Categorie implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	private String nomCategorie;
	@OneToMany(mappedBy = "IdCategorie")
	private Set<SCategorie> Categories;

	public Categorie() {
		super();
	}

	public Categorie(Long id, String nomCategorie) {
		super();
		Id = id;
		this.nomCategorie = nomCategorie;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getNomCategorie() {
		return nomCategorie;
	}

	public void setNomCategorie(String nomCategorie) {
		this.nomCategorie = nomCategorie;
	}

	public Set<SCategorie> getCategories() {
		return Categories;
	}

	public void setCategories(Set<SCategorie> categories) {
		Categories = categories;
	}



}

package tn.esprit.spring.Model;

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
	private String NomCategorie;
	@OneToMany(mappedBy="IdCategorie")
	private Set<SCategorie> Categories;
	
	
	public Categorie() {
	}


	public Categorie(Long id, String nomCategorie, Set<SCategorie> categories) {
		super();
		Id = id;
		NomCategorie = nomCategorie;
		Categories = categories;
	}


	public Long getId() {
		return Id;
	}


	public void setId(Long id) {
		Id = id;
	}


	public String getNomCategorie() {
		return NomCategorie;
	}


	public void setNomCategorie(String nomCategorie) {
		NomCategorie = nomCategorie;
	}


	public Set<SCategorie> getCategories() {
		return Categories;
	}


	public void setCategories(Set<SCategorie> categories) {
		Categories = categories;
	}


}

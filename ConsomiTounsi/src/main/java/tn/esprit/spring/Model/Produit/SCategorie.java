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
	private Long id;
	private String nomSCategorie;
	@ManyToOne
	@JsonIgnore
	private Categorie idCategorie;
	@OneToMany(mappedBy="IdSCategorie",cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<SsCategorie> SsCategories;
	
	public SCategorie() {
	}

	
	public SCategorie(String nomSCategorie, Categorie idCategorie) {
		super();
		this.nomSCategorie = nomSCategorie;
		this.idCategorie = idCategorie;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomSCategorie() {
		return nomSCategorie;
	}

	public void setNomSCategorie(String nomSCategorie) {
		this.nomSCategorie = nomSCategorie;
	}

	public Categorie getIdCategorie() {
		return idCategorie;
	}

	public void setIdCategorie(Categorie idCategorie) {
		this.idCategorie = idCategorie;
	}



	public void setSsCategories(Set<SsCategorie> ssCategories) {
		SsCategories = ssCategories;
	}


	public Set<SsCategorie> getSsCategories() {
		return SsCategories;
	}
	
	
}

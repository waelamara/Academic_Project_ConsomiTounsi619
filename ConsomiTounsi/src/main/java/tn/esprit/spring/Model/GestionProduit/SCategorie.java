package tn.esprit.spring.Model.GestionProduit;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class SCategorie implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	private String NomSCategorie;
	@ManyToOne
	private Categorie IdCategorie;
	@OneToMany(mappedBy="IdSCategorie")
	private Set<SsCategorie> SsCategories;
	
	public SCategorie() {
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

	public Set<SsCategorie> getSsCategories() {
		return SsCategories;
	}

	public void setSsCategories(Set<SsCategorie> ssCategories) {
		SsCategories = ssCategories;
	}
	
	
}

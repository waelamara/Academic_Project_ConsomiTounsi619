package tn.esprit.spring.Model.Forum;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class CategorieSujet implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String NomCategorie;
	@OneToMany(mappedBy="CategorieSujet")
	private Set<Sujet> sujets ;
	 

	
	public CategorieSujet(Long id, String nomCategorie) {
		super();
		this.id = id;
		NomCategorie = nomCategorie;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomCategorie() {
		return NomCategorie;
	}
	public void setNomCategorie(String nomCategorie) {
		NomCategorie = nomCategorie;
	}
	public Set<Sujet> getSujets() {
		return sujets;
	}
	public void setSujets(Set<Sujet> sujets) {
		this.sujets = sujets;
	}
	
}

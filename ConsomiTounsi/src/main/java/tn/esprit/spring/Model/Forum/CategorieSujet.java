package tn.esprit.spring.Model.Forum;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class CategorieSujet implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@Column(unique=true) 
	private String NomCategorie;
	@OneToMany(mappedBy="CategorieSujet",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<Sujet> sujets ;
	
	 

	
	public CategorieSujet() {
		super();
		// TODO Auto-generated constructor stub
	}

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

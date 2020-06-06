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
	private Long id;
	private String nomSsCategorie;
	@OneToMany(mappedBy = "idSsCategorie",cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Produit> Produits;
	@ManyToOne
	@JsonIgnore
	private SCategorie idSCategorie;


	public SsCategorie() {
	}
	

	public SsCategorie(String nomSsCategorie) {
		super();
		this.nomSsCategorie = nomSsCategorie;
	}


	public SsCategorie(String nomSsCategorie, SCategorie idSCategorie) {
		super();
		this.nomSsCategorie = nomSsCategorie;
		this.idSCategorie = idSCategorie;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomSsCategorie() {
		return nomSsCategorie;
	}

	public void setNomSsCategorie(String nomSsCategorie) {
		this.nomSsCategorie = nomSsCategorie;
	}



	public void setProduits(Set<Produit> produits) {
		Produits = produits;
	}

	public SCategorie getIdSCategorie() {
		return idSCategorie;
	}

	public void setIdSCategorie(SCategorie idSCategorie) {
		this.idSCategorie = idSCategorie;
	}


	public Set<Produit> getProduits() {
		return Produits;
	}

}

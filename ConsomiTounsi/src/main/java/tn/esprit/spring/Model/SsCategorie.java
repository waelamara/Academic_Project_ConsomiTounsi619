package tn.esprit.spring.Model;

import java.io.Serializable;
import java.util.Set;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class SsCategorie implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long Id;
	public String NomSsCategorie;
	@OneToMany(mappedBy="IdSsCategorie")
	public Set<Produit> Produits;
	@ManyToOne
	public SCategorie IdSCategorie;
}

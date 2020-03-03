package tn.esprit.spring.Model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

public class CategorieSujet implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long Id;
	public String NomCategorie;
	@OneToMany(mappedBy="idCategorieSujet")
	public Set<Sujet> sujets;
}

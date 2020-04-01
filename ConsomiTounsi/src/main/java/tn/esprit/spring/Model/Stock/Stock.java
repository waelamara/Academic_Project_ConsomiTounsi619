package tn.esprit.spring.Model.Stock;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import tn.esprit.spring.Model.Produit.Produit;
@Entity
public class Stock implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idstock;
	
	private int quantite;
	@OneToOne(mappedBy="IdStock")
	private Produit idProduit;

}

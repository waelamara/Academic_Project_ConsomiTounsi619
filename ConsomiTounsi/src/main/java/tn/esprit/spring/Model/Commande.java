package tn.esprit.spring.Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="Commande")
public class Commande implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	//@Temporal(TemporalType.DATE)
	private LocalDate date;
	private float montant;
	private String status;
	@OneToMany(mappedBy="idcommande")
	public Set<LigneCommande> ligneCommande;
	@OneToOne
	Facture factureid;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDate  getDate() {
		return date;
	}
	public void setDate(LocalDate  date) {
		this.date = date;
	}
	public float getMontant() {
		return montant;
	}
	public void setMontant(float montant) {
		this.montant = montant;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Commande(Long id, LocalDate date, float montant, String status) {
		super();
		this.id = id;
		this.date = date;
		this.montant = montant;
		this.status = status;
	}
	public Commande() {
		super();
	}
	public Commande(LocalDate date, float montant, String status) {
		super();
		this.date = date;
		this.montant = montant;
		this.status = status;
	}
	

}

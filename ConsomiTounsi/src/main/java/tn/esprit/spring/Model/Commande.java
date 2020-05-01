package tn.esprit.spring.Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.Transient;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.annotation.JsonIgnore;



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
	
	private String typedePayment;
	
	private String remise;
	
	private double pourcentageDeRemise;
	
	@JsonIgnore
	@OneToMany(mappedBy="commande",cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	public Set<LigneCommande> ligneCommande;
	
	@OneToOne(mappedBy="commande")
	Facture factureid;
	
	@ManyToOne
	@JoinColumn(name="idUser")
	  private User idUser;
	
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
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="Commande")
	private Set<reclamation> reclamation;

	public String getTypedePayment() {
		return typedePayment;
	}
	
	public void setTypedePayment(String typedePayment) {
		typedePayment = typedePayment;
	}
	
	public User getIdUser() {
		return idUser;
	}
	
	public void setIdUser(User idUser) {
		this.idUser = idUser;
	}
	
	public String getRemise() {
		return remise;
	}
	
	public void setRemise(String remise) {
		this.remise = remise;
	}
	
	
	@OneToOne (mappedBy="Commande")
	Livraison Livraison;

	public Stream<Commande> stream() {
		// TODO Auto-generated method stub
		return null;
	}
	public double getPourcentageDeRemise() {
		return pourcentageDeRemise;
	}
	public void setPourcentageDeRemise(double pourcentageDeRemise) {
		this.pourcentageDeRemise = pourcentageDeRemise;
	}

	public Facture getFactureid() {
		return factureid;
	}

	public void setFactureid(Facture factureid) {
		this.factureid = factureid;
	}
	
	
	

}

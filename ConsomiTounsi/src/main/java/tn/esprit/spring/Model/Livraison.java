package tn.esprit.spring.Model;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "Livraison")

public class Livraison implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// @Column(name="Liv_id",length=15,nullable=false,unique=true)
	// @Transient
	@Column(name = "id")
	private long id;

	@Column(name = "Lieu")
	private String Lieu;
	@Column(name = "Etat")
	private boolean Etat;
	@Column(name = "MoyenLivraison")
	private EMoyenTransportL MoyenTL;
	@Transient
	long commande_id;
	@Transient
	long livreur_id;
	@Column(name = "DateAffecLivr")
	// @Temporal(TemporalType.DATE)
	private LocalDate DateAffecLivr;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLieu() {
		return Lieu;
	}

	public void setLieu(String lieu) {
		Lieu = lieu;
	}

	public EMoyenTransportL getMoyenTL() {
		return MoyenTL;
	}

	public void setMoyenTL(EMoyenTransportL moyenTL) {
		MoyenTL = moyenTL;
	}

	public Boolean getEtat() {
		return Etat;
	}

	public void setEtat(Boolean etat) {
		Etat = etat;
	}

	public long getCommande_id() {
		return commande_id;
	}

	public void setCommande_id(long commande_id) {
		this.commande_id = commande_id;
	}

	public Livreur getLivreur() {
		return Livreur;
	}

	public void setLivreur(Livreur livreur) {
		Livreur = livreur;
	}

	public Commande getCommande() {
		return Commande;
	}

	public void setCommande(Commande commande) {
		Commande = commande;
	}

	public void setEtat(boolean etat) {
		Etat = etat;
	}

	public long getLivreur_id() {
		return livreur_id;
	}

	public void setLivreur_id(long livreur_id) {
		this.livreur_id = livreur_id;
	}

	public LocalDate getDateAffecLivr() {
		return DateAffecLivr;
	}

	public void setDateAffecLivr(LocalDate dateAffecLivr) {
		DateAffecLivr = dateAffecLivr;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Livraison(long id, String lieu, boolean etat, EMoyenTransportL moyenTL, long commande_id, long livreur_id,
			LocalDate dateAffecLivr, tn.esprit.spring.Model.Livreur livreur, tn.esprit.spring.Model.Commande commande) {
		super();
		this.id = id;
		Lieu = lieu;
		Etat = etat;
		MoyenTL = moyenTL;
		this.commande_id = commande_id;
		this.livreur_id = livreur_id;
		DateAffecLivr = dateAffecLivr;
		Livreur = livreur;
		Commande = commande;
	}

	public Livraison() {
		super();
	}

	@ManyToOne
	Livreur Livreur;
	@OneToOne
	Commande Commande;

}

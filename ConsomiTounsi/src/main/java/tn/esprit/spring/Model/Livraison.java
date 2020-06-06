
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

	@Column(name = "Lieux")
	private String lieux;
	@Column(name = "Etat")
	private boolean etat;
	@Column(name = "MoyenLivraison")
	private EMoyenTransportL moyenTL;
	@Transient
	long commande_id;
	@Transient
	long livreur_id;
	@Column(name = "DateAffecLivr")
	// @Temporal(TemporalType.DATE)

	private LocalDate dateAffecLivr;
	private String localdistribu;
	private String user_id_demande; 
	public String tel_user_commande;

	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	

	public String getLieux() {
		return lieux;
	}

	public void setLieux(String lieux) {
		this.lieux = lieux;
	}

	public EMoyenTransportL getMoyenTL() {
		return moyenTL;
	}

	public void setMoyenTL(EMoyenTransportL moyenTL) {
		this.moyenTL = moyenTL;
	}

	public Boolean getEtat() {
		return etat;
	}

	public long getCommande_id() {
		return commande_id;
	}

	public void setCommande_id(long commande_id) {
		this.commande_id = commande_id;
	}
//
//	public Livreur getLivreur() {
//		return Livreur;
//	}
//
//	public void setLivreur(Livreur livreur) {
//		Livreur = livreur;
//	}

	
	


	public User getDelivery() {
		return Delivery;
	}

	

	public String getUser_id_demande() {
		return user_id_demande;
	}

	public void setUser_id_demande(String user_id_demande) {
		this.user_id_demande = user_id_demande;
	}

	public String getTel_user_commande() {
		return tel_user_commande;
	}

	public void setTel_user_commande(String tel_user_commande) {
		this.tel_user_commande = tel_user_commande;
	}

	public void setDelivery(User delivery) {
		Delivery = delivery;
	}


	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	public void setEtat(boolean etat) {
		this.etat = etat;
	}

	public long getLivreur_id() {
		return livreur_id;
	}

	public void setLivreur_id(long livreur_id) {
		this.livreur_id = livreur_id;
	}

	public LocalDate getDateAffecLivr() {
		return dateAffecLivr;
	}

	public void setDateAffecLivr(LocalDate dateAffecLivr) {
		this.dateAffecLivr = dateAffecLivr;
	}


	public String getLocaldistribu() {
		return localdistribu;
	}


	public void setLocaldistribu(String localdistribu) {
		this.localdistribu = localdistribu;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

//	public Livraison(long id, String lieux, boolean etat, EMoyenTransportL moyenTL, long commande_id, long livreur_id,
//			LocalDate dateAffecLivr, tn.esprit.spring.Model.Livreur livreur, tn.esprit.spring.Model.Commande commande,String localdistribu) {
//		super();
//		this.id = id;
//		Lieux = lieux;
//		Etat = etat;
//		MoyenTL = moyenTL;
//		this.commande_id = commande_id;
//		this.livreur_id = livreur_id;
//		DateAffecLivr = dateAffecLivr;
//		Livreur = livreur;
//		Commande = commande;
//		Localdistribu=localdistribu;
//	}

	public Livraison() {
		super();
	}

	
	@OneToOne
	Commande commande;
	@ManyToOne
	User Delivery;


}



package tn.esprit.spring.Model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table (name ="Livreur")
//@EntityListeners(AuditingEntityListener.class)
public class Livreur implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	//@Column(name="Liv_id",length=15,nullable=false,unique=true)
	//@Transient
	public long id ;
	
	
	private String Nom ;
	
	
	private String Prenom;
	
	
	private String Email;
	
	
	private int Telephone;
	
	@Temporal(TemporalType.DATE)
	private Date DatedeNaissance;
	
	
	private int NbMission;
	
	
	private String LieuxTravail;


	


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}



	public String getNom() {
		return Nom;
	}


	public void setNom(String nom) {
		Nom = nom;
	}


	public String getPrenom() {
		return Prenom;
	}


	public void setPrenom(String prenom) {
		Prenom = prenom;
	}


	public String getEmail() {
		return Email;
	}


	public void setEmail(String email) {
		Email = email;
	}


	public int getTelephone() {
		return Telephone;
	}


	public void setTelephone(int telephone) {
		Telephone = telephone;
	}


	public Date getDatedeNaissance() {
		return DatedeNaissance;
	}


	public void setDatedeNaissance(Date datedeNaissance) {
		DatedeNaissance = datedeNaissance;
	}


	public int getNbMission() {
		return NbMission;
	}


	public void setNbMission(int nbMission) {
		NbMission = nbMission;
	}


	public String getLieuxTravail() {
		return LieuxTravail;
	}


	public void setLieuxTravail(String lieuxTravail) {
		LieuxTravail = lieuxTravail;
	}


	public Livreur(int id, String nom, String prenom, String email, int telephone, Date datedeNaissance, int nbMission,
			String lieuxTravail) {
		super();
		this.id = id;
		Nom = nom;
		Prenom = prenom;
		Email = email;
		Telephone = telephone;
		DatedeNaissance = datedeNaissance;
		NbMission = nbMission;
		LieuxTravail = lieuxTravail;
	}
	


	public Livreur() {
	
	}
	@OneToMany(cascade = CascadeType.ALL, mappedBy="Livreur")
	private Set<Livraison> Livraison;



	public Set<Livraison> getLivraison() {
		return Livraison;
	}


	public void setLivraison(Set<Livraison> livraison) {
		Livraison = livraison;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public String toString() {
		return "Livreur [Nom=" + Nom + ", Prenom=" + Prenom + ", Email=" + Email + ", Telephone=" + Telephone
				+ ", DatedeNaissance=" + DatedeNaissance + ", NbMission=" + NbMission + ", LieuxTravail=" + LieuxTravail
				 + ", Livraison=" + Livraison + "]";
	}
	
	
	
	
	
	
	

}

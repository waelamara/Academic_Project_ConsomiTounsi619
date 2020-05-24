package tn.esprit.spring.Model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
@Table(name = "Livreur")
// @EntityListeners(AuditingEntityListener.class)
public class Livreur implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// @Column(name="Liv_id",length=15,nullable=false,unique=true)
	// @Transient
	public long id;
	@Column(name = "nom")
	private String nom;
	@Column(name = "prenom")
	private String prenom;
	@Column(name = "sexe")
    @Enumerated(EnumType.STRING)
 	private Sexe sexe;
	@Column(name = "email")
	private String email;
	@Column(name = "cin")
	private int cin;
	@Column(name = "telephone")
	private int telephone;
	@Column(name = "datedeNaissance")
	@Temporal(TemporalType.DATE)
	private Date datedeNaissance;
	@Column(name = "nbMission")
	private int nbMission;
	@Column(name = "lieuxTravail")
	private String lieuxTravail;
	@Column(name = "prime")
	private int prime;
	@Column(name = "password")
	private String password;
	@Column(name = "etat")
	private String etat;
	@Column(name = "adresse")
	private String adresse;
	@Column(name = "disponible")
	private String disponible;
	
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getCin() {
		return cin;
	}

	public void setCin(int cin) {
		this.cin = cin;
	}

	public int getTelephone() {
		return telephone;
	}

	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}

	public Date getDatedeNaissance() {
		return datedeNaissance;
	}

	public void setDatedeNaissance(Date datedeNaissance) {
		this.datedeNaissance = datedeNaissance;
	}

	public int getNbMission() {
		return nbMission;
	}

	public void setNbMission(int nbMission) {
		this.nbMission = nbMission;
	}

	public String getLieuxTravail() {
		return lieuxTravail;
	}

	public void setLieuxTravail(String lieuxTravail) {
		this.lieuxTravail = lieuxTravail;
	}

	public int getPrime() {
		return prime;
	}

	public void setPrime(int prime) {
		this.prime = prime;
	}

	public Sexe getSexe() {
		return sexe;
	}

	public void setSexe(Sexe sexe) {
		this.sexe = sexe;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}
	

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	

	public String getDisponible() {
		return disponible;
	}

	public void setDisponible(String disponible) {
		this.disponible = disponible;
	}

	public Livreur(String nom, String prenom, Sexe sexe, String email, int cin, int telephone,
			Date datedeNaissance, String password) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.sexe = sexe;
		this.email = email;
		this.cin = cin;
		this.telephone = telephone;
		this.datedeNaissance = datedeNaissance;
		this.password = password;
		
	}
	
	

	public Livreur( String nom, String prenom, Sexe sexe, String email, int cin, int telephone,
			Date datedeNaissance, String password, String adresse) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.sexe = sexe;
		this.email = email;
		this.cin = cin;
		this.telephone = telephone;
		this.datedeNaissance = datedeNaissance;
		this.password = password;
		this.adresse = adresse;
	}

	public Livreur(String nom, String prenom, String email, int cin, int telephone,Sexe sexe, Date datedeNaissance,
			String password, String adresse) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.sexe = sexe;
		this.email = email;
		this.cin = cin;
		this.telephone = telephone;
		this.datedeNaissance = datedeNaissance;
		this.password = password;
		this.adresse = adresse;
		;
	}
	

	public Livreur(String nom, String prenom, String email, int cin, int telephone,Sexe sexe, Date datedeNaissance,
			int nbMission, String lieuxTravail, int prime, String password, String etat, String adresse,
			String disponible) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.sexe = sexe;
		this.email = email;
		this.cin = cin;
		this.telephone = telephone;
		this.datedeNaissance = datedeNaissance;
		this.nbMission = nbMission;
		this.lieuxTravail = lieuxTravail;
		this.prime = prime;
		this.password = password;
		this.etat = etat;
		this.adresse = adresse;
		this.disponible = disponible;
	}

	public Livreur() {

	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "Livreur")
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
		return "Livreur [Nom=" + nom + ", Prenom=" + prenom + ", Email=" + email + ", Telephone=" + telephone
				+ ", DatedeNaissance=" + datedeNaissance + ", NbMission=" + nbMission + ", LieuxTravail=" + lieuxTravail + "]";
	}

}

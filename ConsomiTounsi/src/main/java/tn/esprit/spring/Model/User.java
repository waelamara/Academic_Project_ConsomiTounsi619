package tn.esprit.spring.Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import tn.esprit.spring.Model.Charite.Charite;
import tn.esprit.spring.Model.Forum.Commentaire;
import tn.esprit.spring.Model.Forum.ImageSujet;
import tn.esprit.spring.Model.Forum.Sujet;
import tn.esprit.spring.Model.Forum.Vote;
import tn.esprit.spring.Model.Forum.VoteSujet;
import tn.esprit.spring.Model.Forum.Vus;
import tn.esprit.spring.Model.Publicite.Publicite;
import tn.esprit.spring.Model.Livraison;

@Entity
@Table(name = "user")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	private Long id;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Transient
	private String passwordConfirm;

	@Column(name = "email")
	private String email;

	@Column(name = "address")
	private String address;

	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dateN;

	@Column(name = "tel")
	private String tel;

	private int pointFidelite;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();
	
	@OneToMany(mappedBy="userId",cascade=CascadeType.ALL)
	@JsonIgnore
	private Set<ImageUser> Images;

	@Column(name = "sexe")
	@Enumerated(EnumType.STRING)
	private Sexe sexe;
	private String interet;

	@Column(name = "EtatAcc")
	private Boolean EtatAcc = true;

	@Column(name = "enabled")
	private boolean enabled = false;

	@Enumerated(EnumType.STRING)
	private AuthProvider provider;

	private String providerId;
	@Column(name = "disponible")
	private String disponible;
	@Column(name = "etat")
	private String etatD;
	@Column(name = "prime")
	private Integer prime;
	@Column(name = "nbMission")
	private Integer nbMission;
	@Column(name = "lieuxTravail")
	private String lieuxTravail;
	@Column(name = "signupDay")
	private LocalDate signupDay;
	
	
	

	public AuthProvider getProvider() {
		return provider;
	}

	public void setProvider(AuthProvider provider) {
		this.provider = provider;
	}

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Boolean getEtatAcc() {
		return EtatAcc;
	}

	public void setEtatAcc(Boolean etatAcc) {
		EtatAcc = etatAcc;
	}

	public String getInteret() {
		return interet;
	}

	public void setInteret(String interet) {
		this.interet = interet;
	}

	@OneToMany(mappedBy = "IdUser", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Publicite> pubs;
	@JsonIgnore
	@OneToMany(mappedBy = "idUser", fetch = FetchType.LAZY)
	private Set<CadeauUser> CadeauUser;

	public User(String username, String email, String password, String firstName, String lastName, String address,
			Date dateN, String tel) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.address = address;
		this.dateN = dateN;
		this.tel = tel;
	}

	public User(String username, String email, String password, String firstName, String lastName, String address,
			Date dateN, String tel, Sexe sexe) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.address = address;
		this.dateN = dateN;
		this.tel = tel;
		this.sexe = sexe;
	}
	
	public User(String username, String email, String password, String firstName, String lastName, String address,
			Date dateN, String tel, Sexe sexe, Set<ImageUser> images) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.address = address;
		this.dateN = dateN;
		this.tel = tel;
		this.sexe = sexe;
		this.Images = images;
	}
	

	public User(String username, String email, String password, String firstName, String lastName, String address,
			String tel) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.address = address;
		this.tel = tel;

	}

	public User(String username, String email, String password) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDateN() {
		return dateN;
	}

	public void setDateN(Date dateN) {
		this.dateN = dateN;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	

	public int getNbMission() {
		return nbMission;
	}

	public void setNbMission(int nbMission) {
		this.nbMission = nbMission;
	}

	@Column(name = "Solde")
	private float solde;
	@OneToMany(mappedBy = "iduser")
	@JsonIgnore
	public Set<Charite> charite;
	@JsonIgnore
	@OneToMany(mappedBy = "idUser", fetch = FetchType.LAZY)

	private Collection<Commande> commandes;
	/****** ayed *******/
	@OneToMany(mappedBy = "idUser")
	@JsonIgnore
	public Set<Commentaire> commentaires;
	@OneToMany(mappedBy = "idUser", fetch = FetchType.EAGER)
	@JsonIgnore
	public Set<Sujet> Sujets;
	@OneToMany(mappedBy = "idUser")
	@JsonIgnore
	public Set<Vote> Votes;
	@OneToMany(mappedBy = "idUser")
	@JsonIgnore
	public Set<VoteSujet> VotesSujet;
	@OneToMany(mappedBy = "idUser")
	@JsonIgnore
	public Set<Vus> vus;

	/********* ayed *********/
	public Long getId() {
		return id;
	}

	public Set<Commentaire> getCommentaires() {
		return commentaires;
	}

	public void setCommentaires(Set<Commentaire> commentaires) {
		this.commentaires = commentaires;
	}

	public Set<Sujet> getSujets() {
		return Sujets;
	}

	public void setSujets(Set<Sujet> sujets) {
		Sujets = sujets;
	}

	public Set<Vote> getVotes() {
		return Votes;
	}

	public void setVotes(Set<Vote> votes) {
		Votes = votes;
	}

	public Set<VoteSujet> getVotesSujet() {
		return VotesSujet;
	}

	public void setVotesSujet(Set<VoteSujet> votesSujet) {
		VotesSujet = votesSujet;
	}

	public Set<Vus> getVus() {
		return vus;
	}

	public void setVus(Set<Vus> vus) {
		this.vus = vus;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;

	}

	public String getDisponible() {
		return disponible;
	}

	public void setDisponible(String disponible) {
		this.disponible = disponible;
	}

	public String getEtatD() {
		return etatD;
	}

	public void setEtatD(String etatD) {
		this.etatD = etatD;
	}

	public int getPrime() {
		return prime;
	}

	public void setPrime(int prime) {
		this.prime = prime;
	}

	public String getLieuxTravail() {
		return lieuxTravail;
	}

	public void setLieuxTravail(String lieuxTravail) {
		this.lieuxTravail = lieuxTravail;
	}

	public Set<Charite> getCharite() {
		return charite;
	}
	

	public void setPrime(Integer prime) {
		this.prime = prime;
	}

	public void setNbMission(Integer nbMission) {
		this.nbMission = nbMission;
	}

	public void setCharite(Set<Charite> charite) {
		this.charite = charite;
	}

	public Collection<Commande> getCommandes() {
		return commandes;
	}

	public void setCommandes(Collection<Commande> commandes) {
		this.commandes = commandes;
	}

	public float getSolde() {
		return solde;
	}

	public void setSolde(float solde) {
		this.solde = solde;
	}

	public static Long getSerialversionuid() {
		return serialVersionUID;
	}

	public User() {
		super();

	}

	public User(Long iduser) {
		super();
		this.id = iduser;

	}

	public Sexe getSexe() {
		return sexe;
	}

	public void setSexe(Sexe sexe) {
		this.sexe = sexe;
	}

	public Set<Publicite> getPubs() {
		return pubs;
	}

	public void setPubs(Set<Publicite> pubs) {
		this.pubs = pubs;
	}

	public int getPointFidelite() {
		return pointFidelite;
	}

	public void setPointFidelite(int pointFidelite) {
		this.pointFidelite = pointFidelite;
	}

	public Set<CadeauUser> getCadeauUser() {
		return CadeauUser;
	}

	public void setCadeauUser(Set<CadeauUser> cadeauUser) {
		CadeauUser = cadeauUser;
	}

	/*************** Oussama ********/
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<reclamation> reclamation;

	public Set<reclamation> getReclamation() {
		return reclamation;
	}

	public void setReclamation(Set<reclamation> reclamation) {
		this.reclamation = reclamation;
	}

	/*** Oussama Livreur relation ****/

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "Delivery")
	private Set<Livraison> Livraison;

	public Set<Livraison> getLivraison() {
		return Livraison;
	}

	public void setLivraison(Set<Livraison> livraison) {
		Livraison = livraison;
	}

	public LocalDate getSignupDay() {
		return signupDay;
	}

	public void setSignupDay(LocalDate signupDay) {
		this.signupDay = signupDay;
	}

	
	@OneToMany(mappedBy = "idUser", fetch = FetchType.EAGER)
	@JsonIgnore
	public Set<Salaire> salaire;
	
	
	

}

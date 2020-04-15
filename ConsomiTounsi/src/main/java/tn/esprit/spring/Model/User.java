package tn.esprit.spring.Model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import tn.esprit.spring.Model.Charite.Charite;
import tn.esprit.spring.Model.Forum.Commentaire;
import tn.esprit.spring.Model.Forum.Sujet;
import tn.esprit.spring.Model.Forum.Vote;
import tn.esprit.spring.Model.Forum.VoteSujet;
import tn.esprit.spring.Model.Publicite.Publicite;



@Entity
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
    
    @Temporal (TemporalType.DATE)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateN;
    
    @Column(name = "tel")
	private String tel;

    private int pointFidelite;
    @ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "user_roles", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();
    
    @Column(name = "sexe")
    @Enumerated(EnumType.STRING)
 	private Sexe sexe;
    private String interet;
    
    public String getInteret() {
		return interet;
	}
	public void setInteret(String interet) {
		this.interet = interet;
	}


	@OneToMany(mappedBy="IdUser",cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Publicite> pubs;
    @JsonIgnore
	@OneToMany(mappedBy="idUser",fetch = FetchType.LAZY)
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
	public User(String firstName, String lastName, String username, String password, String email, String address,
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
	public User(String username, String email, String password, String firstName, String lastName, String address, String tel
			) {
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


	@Column(name = "Solde")
	private float solde;
	@OneToMany(mappedBy="iduser")
	@JsonIgnore
	public Set<Charite> charite;
	@JsonIgnore
	@OneToMany(mappedBy="idUser",fetch = FetchType.LAZY)
	
	private Collection<Commande> commandes;
	/******ayed*******/
	@OneToMany(mappedBy="idUser")
	@JsonIgnore
	public Set<Commentaire> commentaires;
	@OneToMany(mappedBy="idUser")
	@JsonIgnore
	public Set<Sujet> Sujets;
	@OneToMany(mappedBy="idUser")
	@JsonIgnore
	public Set<Vote> Votes;
	@OneToMany(mappedBy="idUser")
	@JsonIgnore
	public Set<VoteSujet> VotesSujet;
	/*********ayed*********/
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

	public Set<Charite> getCharite() {
		return charite;
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
		this.id=iduser;

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


	/***************Oussama********/
	@OneToMany(cascade = CascadeType.ALL, mappedBy="User")
	private Set<reclamation> reclamation;

	public Set<reclamation> getReclamation() {
		return reclamation;
	}

	public void setReclamation(Set<reclamation> reclamation) {
		this.reclamation = reclamation;
	}
}

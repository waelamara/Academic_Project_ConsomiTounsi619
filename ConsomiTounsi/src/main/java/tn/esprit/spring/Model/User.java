package tn.esprit.spring.Model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import tn.esprit.spring.Model.Forum.Commentaire;
import tn.esprit.spring.Model.Forum.Sujet;
import tn.esprit.spring.Model.Forum.Vote;
import tn.esprit.spring.Model.Forum.VoteSujet;



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
	@OneToMany(mappedBy="iduser")
	public Set<Charite> charite;
	@JsonIgnore
	@OneToMany(mappedBy="idUser",fetch = FetchType.LAZY)
	
	private Collection<Commande> commandes;
	/******ayed*******/
	@OneToMany(mappedBy="idUser")
	public Set<Commentaire> commentaires;
	@OneToMany(mappedBy="idUser")
	public Set<Sujet> Sujets;
	@OneToMany(mappedBy="idUser")
	public Set<Vote> Votes;
	@OneToMany(mappedBy="idUser")
	public Set<VoteSujet> VotesSujet;
	/*********ayed*********/
}

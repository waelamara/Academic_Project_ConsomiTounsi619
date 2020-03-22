package tn.esprit.spring.Model;

import tn.esprit.spring.Model.Forum.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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

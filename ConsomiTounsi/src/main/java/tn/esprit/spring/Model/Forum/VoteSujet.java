package tn.esprit.spring.Model.Forum;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import tn.esprit.spring.Model.User;
@Entity 
public class VoteSujet implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "VOTESUJET_ID")
	private Long id;
    private int nbLike;
	private int nbDislike;
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "idUser", referencedColumnName = "USER_ID")
	User idUser;
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "idSujet", referencedColumnName = "id")
	Sujet idSujet;
	
	public VoteSujet() {
		super();
	}
	
	public VoteSujet(Long id, int nbLike, int nbDislike) {
		super();
		this.id = id;
		this.nbLike = nbLike;
		this.nbDislike = nbDislike;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getNbLike() {
		return nbLike;
	}
	public void setNbLike(int nbLike) {
		this.nbLike = nbLike;
	}
	public int getNbDislike() {
		return nbDislike;
	}
	public void setNbDislike(int nbDislike) {
		this.nbDislike = nbDislike;
	}
	public User getIdUser() {
		return idUser;
	}
	public void setIdUser(User idUser) {
		this.idUser = idUser;
	}
	public Sujet getIdSujet() {
		return idSujet;
	}
	public void setIdSujet(Sujet idSujet) {
		this.idSujet = idSujet;
	}
	

}

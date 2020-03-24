package tn.esprit.spring.Model.Forum;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import tn.esprit.spring.Model.*;
@Entity
public class Sujet implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
	private String nomSujet;
	private String description;
	@Temporal (TemporalType.DATE)
    private Date dateAjout;
	private int nbVue;
	private int nbLike;
	private int nbDislike;
	
	@OneToMany(mappedBy="idSujet")
	public Set<VoteSujet> votesSujet;
	@ManyToOne
	@JoinColumn(name = "idCategorieSujet", referencedColumnName = "id")
	CategorieSujet CategorieSujet;
	@ManyToOne
	@JoinColumn(name = "idUser", referencedColumnName = "USER_ID")
	User idUser;
	
	
	public Sujet() {
		super();
	}
	public Sujet(Long id, String nomSujet, String description, Date dateAjout, int nbVue, int nbLike, int nbDislike) {
		super();
		this.id = id;
		this.nomSujet = nomSujet;
		this.description = description;
		this.dateAjout = dateAjout;
		this.nbVue = nbVue;
		this.nbLike = nbLike;
		this.nbDislike = nbDislike;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomSujet() {
		return nomSujet;
	}
	public void setNomSujet(String nomSujet) {
		this.nomSujet = nomSujet;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDateAjout() {
		return dateAjout;
	}
	public void setDateAjout(Date dateAjout) {
		this.dateAjout = dateAjout;
	}
	public int getNbVue() {
		return nbVue;
	}
	public void setNbVue(int nbVue) {
		this.nbVue = nbVue;
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
	public Set<VoteSujet> getVotesSujet() {
		return votesSujet;
	}
	public void setVotesSujet(Set<VoteSujet> votesSujet1) {
		votesSujet = votesSujet1;
	}
	public CategorieSujet getCategorieSujet() {
		return CategorieSujet;
	}
	public void setCategorieSujet(CategorieSujet categorieSujet) {
		CategorieSujet = categorieSujet;
	}
	public User getIdUser() {
		return idUser;
	}
	public void setIdUser(User idUser) {
		this.idUser = idUser;
	}
}

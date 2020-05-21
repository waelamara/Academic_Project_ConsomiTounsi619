package tn.esprit.spring.Model.Forum;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import tn.esprit.spring.Model.User;

@Entity
public class Vus implements Serializable  {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
	private int nbVus;
	@Temporal (TemporalType.DATE)
    private Date dateAjout;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "idUser", referencedColumnName = "USER_ID")
	User idUser;
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "idSujet", referencedColumnName = "id")
	Sujet idSujet;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNbVus() {
		return nbVus;
	}

	public void setNbVus(int nbVus) {
		this.nbVus = nbVus;
	}

	public Date getDateAjout() {
		return dateAjout;
	}

	public void setDateAjout(Date dateAjout) {
		this.dateAjout = dateAjout;
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

	public Vus(Long id, int nbVus, Date dateAjout) {
		super();
		this.id = id;
		this.nbVus = nbVus;
		this.dateAjout = dateAjout;
	}

	public Vus() {
		super();
	}
	
}

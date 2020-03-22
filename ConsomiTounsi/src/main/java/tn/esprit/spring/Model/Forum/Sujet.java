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
	public Set<VoteSujet> VotesSujet;
	@ManyToOne
	@JoinColumn(name = "idCategorieSujet", referencedColumnName = "id")
	CategorieSujet CategorieSujet;
	@ManyToOne
	@JoinColumn(name = "idUser", referencedColumnName = "USER_ID")
	User idUser;
}

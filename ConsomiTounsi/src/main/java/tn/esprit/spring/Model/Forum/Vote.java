package tn.esprit.spring.Model.Forum;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import tn.esprit.spring.Model.User;
@Entity
public class Vote implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "VOTE_ID")
	private Long id;
    private int nbLike;
	private int nbDislike;
	@ManyToOne
	@JoinColumn(name = "idUser", referencedColumnName = "USER_ID")
	User idUser;
	@ManyToOne
	@JoinColumn(name = "idCommentaire", referencedColumnName = "id")
	Commentaire idCommentaire;
}

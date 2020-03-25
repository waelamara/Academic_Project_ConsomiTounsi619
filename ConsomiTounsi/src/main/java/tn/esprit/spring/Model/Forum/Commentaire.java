package tn.esprit.spring.Model.Forum;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import tn.esprit.spring.Model.*;

@Entity
public class Commentaire implements Serializable  {
	
		private static final long serialVersionUID = 1L;

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		private String description;
		private int nbLike;
		private int nbDislike;
		
		@ManyToOne  
		@JoinColumn(name = "idUser", referencedColumnName = "USER_ID")
		User idUser;
		@ManyToOne  
		@JoinColumn(name = "idSujet", referencedColumnName = "id")
		Sujet idSujet;
		@OneToMany(mappedBy="idCommentaire")
		@JsonIgnore
		public Set<Vote> Votes;
		
		public Commentaire(){
			super();
		}
		
		public Commentaire(Long id, String description, int nbLike, int nbDislike) {
			super();
			this.id = id;
			this.description = description;
			this.nbLike = nbLike;
			this.nbDislike = nbDislike;
		}
		
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
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
		public Set<Vote> getVotes() {
			return Votes;
		}
		public void setVotes(Set<Vote> votes) {
			Votes = votes;
		}
		
}

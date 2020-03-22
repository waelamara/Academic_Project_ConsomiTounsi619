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
		@OneToMany(mappedBy="idCommentaire")
		public Set<Vote> Votes;
}

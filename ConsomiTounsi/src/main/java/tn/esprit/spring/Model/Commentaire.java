package tn.esprit.spring.Model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

public class Commentaire implements Serializable  {
	
		private static final long serialVersionUID = 1L;

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		private String description;
		private int nbLike;
		private int nbDislike;
		@OneToMany(mappedBy="idCommentaire")
		private Set<Vote>votes;

}

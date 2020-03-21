package tn.esprit.spring.Model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name ="reclamation")

public class reclamation implements Serializable {
	

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	//@Column(name="Liv_id",length=15,nullable=false,unique=true)
	//@Transient
	public long id ;
	
	private String Titre ;
	
	private String Description ;
	
	private boolean Traiter ;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitre() {
		return Titre;
	}

	public void setTitre(String titre) {
		Titre = titre;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public boolean isTraiter() {
		return Traiter;
	}

	public void setTraiter(boolean traiter) {
		Traiter = traiter;
	}

	public String getReponse() {
		return Reponse;
	}

	public void setReponse(String reponse) {
		Reponse = reponse;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private String Reponse ;
	
	

}

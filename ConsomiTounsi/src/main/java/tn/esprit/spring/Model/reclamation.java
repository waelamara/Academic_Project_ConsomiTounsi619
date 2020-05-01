package tn.esprit.spring.Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table (name ="reclamation")

public class reclamation implements Serializable {
	

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	//@Column(name="Liv_id",length=15,nullable=false,unique=true)
	//@Transient
	public long id ;
	
	private String titre ;
	
	private String description ;
	
	private boolean traiter ;
	
	private String etat ;
	
	private LocalDate DateRec;
	
	@Transient
	long commande_id;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isTraiter() {
		return traiter;
	}

	public void setTraiter(boolean traiter) {
		this.traiter = traiter;
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
	@ManyToOne
	@JoinColumn(name = "user_id")
	User User ;


	public User getUser() {
		return User;
	}

	public void setUser(User user) {
		User = user;
	}

	public reclamation() {
		super();
	}


	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public LocalDate getDateRec() {
		return DateRec;
	}

	public void setDateRec(LocalDate dateRec) {
		DateRec = dateRec;
	}

	@ManyToOne
	@JoinColumn(name = "commande_id")
	Commande Commande;

	public Commande getCommande() {
		return Commande;
	}

	public void setCommande(Commande commande) {
		
		Commande = commande;
	}

	public long getCommande_id() {
		return commande_id;
	}

	public void setCommande_id(int commande_id) {
		this.commande_id = commande_id;
	}

	public reclamation(long id, String titre, String description, long commande_id) {
		super();
		this.id = id;
		this.titre = titre;
		this.description = description;
		this.commande_id = commande_id;
	}

	public reclamation(long id, String titre, String description) {
		super();
		this.id = id;
		this.titre = titre;
		this.description = description;
	}

	public reclamation(String titre, String description) {
		super();
		this.titre = titre;
		this.description = description;
	}

	public reclamation(long id, String titre, String description, boolean traiter, String etat, LocalDate dateRec) {
		super();
		this.id = id;
		this.titre = titre;
		this.description = description;
		this.traiter = traiter;
		this.etat = etat;
		DateRec = dateRec;
	}

	
	
	
	



	
	

}

package tn.esprit.spring.Model.Publicite;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import tn.esprit.spring.Model.User;

@Entity
public class Publicite implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	private String nom;
	@Enumerated(EnumType.STRING)
	private Canal canal;
	@Enumerated(EnumType.STRING)
	private GenderCible genderCible;
	@Temporal(TemporalType.DATE)
	private Date dateDebut;
	@Temporal(TemporalType.DATE)
	private Date dateFin;
	private int nbrInitialVueCible;
	private int nbrFinalVue;
	private int debutAgeCible;
	private int finAgeCible;
	private float cout;
	private String video;
	private String image;
	private String emailProprietaire;
	private String numeroProprietaire;
	@ManyToOne
	private User IdUser;

	public Publicite() {
	}
	

	public Publicite(String nom,Canal canal,
			GenderCible genderCible, Date dateDebut, Date dateFin,
			int nbrInitialVueCible, int nbrFinalVue, int debutAgeCible, int finAgeCible, float cout, String video,
			String image, User idUser) {
		super();
		this.nom = nom;
		this.canal = canal;
		this.genderCible = genderCible;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.nbrInitialVueCible = nbrInitialVueCible;
		this.nbrFinalVue = nbrFinalVue;
		this.debutAgeCible = debutAgeCible;
		this.finAgeCible = finAgeCible;
		this.cout = cout;
		this.video = video;
		this.image = image;
		this.IdUser = idUser;
	}


	public Publicite(String nom, Canal canal, Date dateDebut, Date dateFin,
			int nbrInitialVueCible, int nbrFinalVue, float cout, String video, String image, User idUser) {
		super();
		this.nom = nom;
		this.canal = canal;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.nbrInitialVueCible = nbrInitialVueCible;
		this.nbrFinalVue = nbrFinalVue;
		this.cout = cout;
		this.video = video;
		this.image = image;
		this.IdUser = idUser;
	}

	public Publicite(String nom, Canal canal, Date dateDebut, Date dateFin,
			int nbrInitialVueCible, int nbrFinalVue, float cout, String video, String image) {
		super();
		this.nom = nom;
		this.canal = canal;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.nbrInitialVueCible = nbrInitialVueCible;
		this.nbrFinalVue = nbrFinalVue;
		this.cout = cout;
		this.video = video;
		this.image = image;
	}
	
	

	public Publicite(String nom, Canal canal, GenderCible genderCible, Date dateDebut, Date dateFin, int debutAgeCible,
			int finAgeCible) {
		super();
		this.nom = nom;
		this.canal = canal;
		this.genderCible = genderCible;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.debutAgeCible = debutAgeCible;
		this.finAgeCible = finAgeCible;
	}
	
	
	
	


	public Publicite(String nom, Canal canal, GenderCible genderCible, Date dateDebut, Date dateFin, int debutAgeCible,
			int finAgeCible, String emailProprietaire, String numeroProprietaire) {
		super();
		this.nom = nom;
		this.canal = canal;
		this.genderCible = genderCible;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.debutAgeCible = debutAgeCible;
		this.finAgeCible = finAgeCible;
		this.emailProprietaire = emailProprietaire;
		this.numeroProprietaire = numeroProprietaire;
	}


	public Publicite(Long id, String nom, Canal canal, GenderCible genderCible, Date dateDebut, Date dateFin,
			int debutAgeCible, int finAgeCible) {
		super();
		Id = id;
		this.nom = nom;
		this.canal = canal;
		this.genderCible = genderCible;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.debutAgeCible = debutAgeCible;
		this.finAgeCible = finAgeCible;
	}
	


	public Publicite(Long id, String nom, Canal canal, GenderCible genderCible, Date dateDebut, Date dateFin,
			int nbrFinalVue, int debutAgeCible, int finAgeCible, String emailProprietaire, String numeroProprietaire) {
		super();
		Id = id;
		this.nom = nom;
		this.canal = canal;
		this.genderCible = genderCible;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.nbrFinalVue = nbrFinalVue;
		this.debutAgeCible = debutAgeCible;
		this.finAgeCible = finAgeCible;
		this.emailProprietaire = emailProprietaire;
		this.numeroProprietaire = numeroProprietaire;
	}


	public Publicite(Long id, String nom, Canal canal, GenderCible genderCible, Date dateDebut, Date dateFin,
			int debutAgeCible, int finAgeCible, String video, String image) {
		super();
		Id = id;
		this.nom = nom;
		this.canal = canal;
		this.genderCible = genderCible;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.debutAgeCible = debutAgeCible;
		this.finAgeCible = finAgeCible;
		this.video = video;
		this.image = image;
	}


	public Publicite(Long id, String nom, Canal canal, GenderCible genderCible, Date dateDebut, Date dateFin,int nbrFinalVue,
			int debutAgeCible, int finAgeCible, String video, String image, String emailProprietaire,
			String numeroProprietaire) {
		super();
		Id = id;
		this.nom = nom;
		this.canal = canal;
		this.genderCible = genderCible;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.nbrFinalVue=nbrFinalVue;
		this.debutAgeCible = debutAgeCible;
		this.finAgeCible = finAgeCible;
		this.video = video;
		this.image = image;
		this.emailProprietaire = emailProprietaire;
		this.numeroProprietaire = numeroProprietaire;
	}


	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Canal getCanal() {
		return canal;
	}

	public void setCanal(Canal canal) {
		this.canal = canal;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public int getNbrInitialVueCible() {
		return nbrInitialVueCible;
	}

	public void setNbrInitialVueCible(int nbrInitialVueCible) {
		this.nbrInitialVueCible = nbrInitialVueCible;
	}

	public int getNbrFinalVue() {
		return nbrFinalVue;
	}

	public void setNbrFinalVue(int nbrFinalVue) {
		this.nbrFinalVue = nbrFinalVue;
	}

	public float getCout() {
		return cout;
	}

	public void setCout(float cout) {
		this.cout = cout;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public User getIdUser() {
		return IdUser;
	}

	public void setIdUser(User idUser) {
		IdUser = idUser;
	}

	public GenderCible getGenderCible() {
		return genderCible;
	}

	public void setGenderCible(GenderCible genderCible) {
		this.genderCible = genderCible;
	}

	public int getDebutAgeCible() {
		return debutAgeCible;
	}

	public void setDebutAgeCible(int debutAgeCible) {
		this.debutAgeCible = debutAgeCible;
	}

	public int getFinAgeCible() {
		return finAgeCible;
	}

	public void setFinAgeCible(int finAgeCible) {
		this.finAgeCible = finAgeCible;
	}


	public String getEmailProprietaire() {
		return emailProprietaire;
	}


	public void setEmailProprietaire(String emailProprietaire) {
		this.emailProprietaire = emailProprietaire;
	}


	public String getNumeroProprietaire() {
		return numeroProprietaire;
	}


	public void setNumeroProprietaire(String numeroProprietaire) {
		this.numeroProprietaire = numeroProprietaire;
	}

}

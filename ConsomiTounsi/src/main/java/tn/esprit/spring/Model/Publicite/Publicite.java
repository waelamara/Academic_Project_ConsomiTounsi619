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
	private String Nom;
	@Enumerated(EnumType.STRING)
	private Canal Canal;
	@Temporal(TemporalType.DATE)
	private Date DateDebut;
	@Temporal(TemporalType.DATE)
	private Date DateFin;
	private int NbrInitialVueCible;
	private int NbrFinalVue;
	private float Cout;
	private String Video;
	private String Image;
	@ManyToOne
	private User IdUser;

	public Publicite() {
	}

	
	
	public Publicite(String nom, tn.esprit.spring.Model.Publicite.Canal canal, Date dateDebut, Date dateFin,
			int nbrInitialVueCible, int nbrFinalVue, float cout, String video, String image, User idUser) {
		super();
		Nom = nom;
		Canal = canal;
		DateDebut = dateDebut;
		DateFin = dateFin;
		NbrInitialVueCible = nbrInitialVueCible;
		NbrFinalVue = nbrFinalVue;
		Cout = cout;
		Video = video;
		Image = image;
		IdUser = idUser;
	}



	public Publicite(String nom, tn.esprit.spring.Model.Publicite.Canal canal, Date dateDebut, Date dateFin,
			int nbrInitialVueCible, int nbrFinalVue, float cout, String video, String image) {
		super();
		Nom = nom;
		Canal = canal;
		DateDebut = dateDebut;
		DateFin = dateFin;
		NbrInitialVueCible = nbrInitialVueCible;
		NbrFinalVue = nbrFinalVue;
		Cout = cout;
		Video = video;
		Image = image;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getNom() {
		return Nom;
	}

	public void setNom(String nom) {
		Nom = nom;
	}

	public Canal getCanal() {
		return Canal;
	}

	public void setCanal(Canal canal) {
		Canal = canal;
	}

	public Date getDateDebut() {
		return DateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		DateDebut = dateDebut;
	}

	public Date getDateFin() {
		return DateFin;
	}

	public void setDateFin(Date dateFin) {
		DateFin = dateFin;
	}

	public int getNbrInitialVueCible() {
		return NbrInitialVueCible;
	}

	public void setNbrInitialVueCible(int nbrInitialVueCible) {
		NbrInitialVueCible = nbrInitialVueCible;
	}

	public int getNbrFinalVue() {
		return NbrFinalVue;
	}

	public void setNbrFinalVue(int nbrFinalVue) {
		NbrFinalVue = nbrFinalVue;
	}

	public float getCout() {
		return Cout;
	}

	public void setCout(float cout) {
		Cout = cout;
	}

	public String getVideo() {
		return Video;
	}

	public void setVideo(String video) {
		Video = video;
	}

	public String getImage() {
		return Image;
	}

	public void setImage(String image) {
		Image = image;
	}

	public User getIdUser() {
		return IdUser;
	}

	public void setIdUser(User idUser) {
		IdUser = idUser;
	}

}

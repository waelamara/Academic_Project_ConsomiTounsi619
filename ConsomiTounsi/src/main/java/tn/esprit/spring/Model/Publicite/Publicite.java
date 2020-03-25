package tn.esprit.spring.Model.Publicite;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	private String Canal;
	@Temporal (TemporalType.DATE)
	private Date DateDebut;
	@Temporal (TemporalType.DATE)
	private Date DateFin;
	private int NbrInitialVueCible;
	private int NbrFinalVue;
	private float Cout;
	private String Video;
	private String Image;
	
	public Publicite() {
	}
 
	public Publicite(String nom, String canal, Date dateDebut, Date dateFin, int nbrInitialVueCible, int nbrFinalVue,
			float cout, String video, String image) {
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
	

	public Publicite(String nom, String canal, Date dateDebut, Date dateFin, int nbrInitialVueCible, int nbrFinalVue,
			float cout) {
		super();
		Nom = nom;
		Canal = canal;
		DateDebut = dateDebut;
		DateFin = dateFin;
		NbrInitialVueCible = nbrInitialVueCible;
		NbrFinalVue = nbrFinalVue;
		Cout = cout;
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

	public String getCanal() {
		return Canal;
	}

	public void setCanal(String canal) {
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
	
	
	
}

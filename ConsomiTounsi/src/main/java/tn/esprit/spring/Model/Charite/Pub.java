package tn.esprit.spring.Model.Charite;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "T_PUB")
public class Pub implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	private String nom;
	@Temporal (TemporalType.DATE)
	private Date dateDebut;
	@Temporal (TemporalType.DATE)
	private Date dateFin;
	private String image;
	@OneToOne(fetch=FetchType.EAGER,mappedBy="publicite") 
	@JsonIgnore
	private Events events;
	
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
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Events getEvents() {
		return events;
	}
	public void setEvents(Events events) {
		this.events = events;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Pub() {
		super();

	}
	public Pub(String Nom,Date DateDebut,Date DateFin,String Image,Events events) {
		super();
		this.nom=Nom;
		this.dateDebut=DateDebut;
		this.dateFin=DateFin;
		this.image=Image;
		this.events=events;

	}
	public Pub(String Nom,Date DateDebut,Date DateFin,Events events) {
		super();
		this.nom=Nom;
		this.dateDebut=DateDebut;
		this.dateFin=DateFin;
		this.events=events;

	}

}

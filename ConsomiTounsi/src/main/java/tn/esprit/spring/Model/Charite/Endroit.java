package tn.esprit.spring.Model.Charite;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import tn.esprit.spring.Model.Charite.Events;

@Entity
@Table(name = "T_ENDROIT")
public class Endroit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	private int prix;
	private String statu;
	private String emplacement;
	private int nbplace;
	@ManyToOne 
	@JsonIgnore
	Events eventss;
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public int getPrix() {
		return prix;
	}
	public void setPrix(int prix) {
		this.prix = prix;
	}
	public String getStatu() {
		return statu;
	}
	public void setStatu(String statu) {
		this.statu = statu;
	}
	public String getEmplacement() {
		return emplacement;
	}
	public void setEmplacement(String emplacement) {
		this.emplacement = emplacement;
	}
	public int getNbplace() {
		return nbplace;
	}
	public void setNbplace(int nbplace) {
		this.nbplace = nbplace;
	}
	public Events getEventss() {
		return eventss;
	}
	public void setEventss(Events eventss) {
		this.eventss = eventss;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	} 
	

}

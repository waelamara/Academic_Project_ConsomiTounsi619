package tn.esprit.spring.Model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Livraison")

public class Livraison implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// @Column(name="Liv_id",length=15,nullable=false,unique=true)
	// @Transient
	@Column(name = "id")
	private int id;

	@Column(name = "Lieu")
	private String Lieu;
	@Column(name = "Etat")
	private String Etat;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLieu() {
		return Lieu;
	}

	public void setLieu(String lieu) {
		Lieu = lieu;
	}

	public String getEtat() {
		return Etat;
	}

	public void setEtat(String etat) {
		Etat = etat;
	}

	

	public Livraison(int id, String lieu, String etat) {
		super();
		this.id = id;
		Lieu = lieu;
		Etat = etat;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@ManyToOne
	Livreur Livreur;

	


	
}

package tn.esprit.spring.Model;

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
	public Long Id;
	public String Nom;
	public String Canal;
	@Temporal (TemporalType.DATE)
	public Date DateDebut;
	@Temporal (TemporalType.DATE)
	public Date DateFin;
	public int NbrInitialVueCible;
	public int NbrFinalVue;
	public float Cout;
	public String Video;
	public String Image;
	
	
	
}

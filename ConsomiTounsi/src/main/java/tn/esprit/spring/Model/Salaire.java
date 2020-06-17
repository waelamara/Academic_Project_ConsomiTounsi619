package tn.esprit.spring.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "salaire")
public class Salaire {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idUser")
	private Long idUser;
	
	private String salaireNet;
	
	private String salaireBrut;
	
	private String tax;
	
	@Enumerated(EnumType.STRING)
	private Mois mois;


	


	public Long getIdUser() {
		return idUser;
	}


	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}


	public String getSalaireNet() {
		return salaireNet;
	}


	public void setSalaireNet(String salaireNet) {
		this.salaireNet = salaireNet;
	}


	public String getSalaireBrut() {
		return salaireBrut;
	}


	public void setSalaireBrut(String salaireBrut) {
		this.salaireBrut = salaireBrut;
	}


	public String getTax() {
		return tax;
	}


	public void setTax(String tax) {
		this.tax = tax;
	}


	public Mois getMois() {
		return mois;
	}


	public void setMois(Mois mois) {
		this.mois = mois;
	}
	
	
	
	
	
	

}

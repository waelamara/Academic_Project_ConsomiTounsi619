package tn.esprit.spring.Controller.Livreur;



import java.util.Date;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.Model.Livreur;
import tn.esprit.spring.Model.Sexe;
import tn.esprit.spring.Service.Livreur.LivreurService;

@Controller(value = "SignupDeliveryController")
@ELBeanName(value = "SignupDeliveryController")
@Join(path = "/signupdeliverym", to = "/signupDeliveryMan.jsf")
@Component
public class SignupDeliveryController {
	
	@Autowired
	LivreurService LivreurService;
	@Autowired
	PasswordEncoder encoder;
	
	private String nom;
	private String prenom;
    private Sexe sexe;	
	private String email;	
	private int cin;	
	private int telephone;
	private Date dateN;
	private String password;
	private String adresse;
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public Sexe getSexe() {
		return sexe;
	}
	public void setSexe(Sexe sexe) {
		this.sexe = sexe;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getCin() {
		return cin;
	}
	public void setCin(int cin) {
		this.cin = cin;
	}
	public int getTelephone() {
		return telephone;
	}
	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}
	public Date getDateN() {
		return dateN;
	}
	public void setDateN(Date dateN) {
		this.dateN = dateN;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String doSignup() {
		String navigateTo = "/acceuil.xhtml"; 
		Livreur liv =new Livreur(nom,prenom,email,cin,telephone,sexe,dateN, encoder.encode(password),adresse); 
		liv.setEtat("Waiting");
		liv.setNbMission(0);
		liv.setPrime(0);
		liv.setDisponible("waiting");
		LivreurService.save(liv);
		return navigateTo;
	}
	
	

	
}

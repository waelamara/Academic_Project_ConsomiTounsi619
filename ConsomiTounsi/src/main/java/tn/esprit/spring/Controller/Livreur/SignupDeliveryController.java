
package tn.esprit.spring.Controller.Livreur;



import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.Model.ERole;
import tn.esprit.spring.Model.Role;
import tn.esprit.spring.Model.Sexe;
import tn.esprit.spring.Model.User;
import tn.esprit.spring.Repository.RoleRepository;
import tn.esprit.spring.Repository.UserRepository;
import tn.esprit.spring.Service.GestionUser.OnRegistrationCompleteEvent;
import tn.esprit.spring.Service.Livraison.LivraisonService;

@Controller(value = "SignupDeliveryController")
@ELBeanName(value = "SignupDeliveryController")
@Join(path = "/signupdeliverym", to = "/signupDeliveryMan.jsf")
@Component
public class SignupDeliveryController {
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	LivraisonService L;

	@Autowired
	PasswordEncoder encoder;
	@Autowired
	ApplicationEventPublisher eventPublisher;
	
	private String nom;
	private String prenom;
    private Sexe sexe;	
	private String email;	
	private String username;	
	private String telephone;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
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
//	public String doSignup() {
//		String navigateTo = "/acceuil.xhtml"; 
//		Livreur liv =new Livreur(nom,prenom,email,USERNAMR,telephone,sexe,dateN, encoder.encode(password),adresse); 
//		liv.setEtat("Waiting");
//		liv.setNbMission(0);
//		liv.setPrime(0);
//		liv.setDisponible("waiting");
//		LivreurService.save(liv);
//		return navigateTo;
//	}
	
	public String doSignup() {
		String navigateTo = "null";
		if (userRepository.existsByUsername(username)) {
			FacesMessage facesMessage =

					new FacesMessage("Error: Username is already taken!");

					FacesContext.getCurrentInstance().addMessage("form1:btn",facesMessage);
		}

		else if (userRepository.existsByEmail(email)) {
			FacesMessage facesMessage =

					new FacesMessage("Error: Email is already in use!");

					FacesContext.getCurrentInstance().addMessage("form1:btn",facesMessage);
		}
		else
		{
			User user = new User(username, 
					 email,
					 encoder.encode(password),prenom
					 ,nom,adresse,dateN,telephone
					 ,sexe);
			Set<Role> roles = new HashSet<>();
			Role userRole = roleRepository.findByName(ERole.ROLE_LIVREUR)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
			user.setRoles(roles);
			user.setDisponible("waiting");
			user.setPrime(0);
			//calcule le mindistance et affectation lieux de travail livreur
			double distanceAriana=L.calculedistanceArianna(adresse);
			double distanceSousse=L.calculedistanceSousse(adresse);
			double distanceGabes=L.calculedistanceGabes(adresse);
	        double Mindistance =Math.min(distanceAriana, Math.min(distanceSousse, distanceGabes));
		    if(Mindistance==distanceAriana){   user.setLieuxTravail("Arianna");}
		    else if (Mindistance==distanceSousse) { 
		    	user.setLieuxTravail("Sousse");
		    }
		    else {user.setLieuxTravail("Gabes"); }
		    //fin
		
		user.setNbMission(0);
		user.setEtatD("waiting");
			userRepository.save(user);
			String appUrl = "";
			User registered= user;
			eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, appUrl));
			navigateTo="login.xhtml?faces-redirect=true";
			FacesMessage facesMessage =

					new FacesMessage("Registered successfully, please verify your account!");

					FacesContext.getCurrentInstance().addMessage("form:btn",facesMessage);
		}
		return navigateTo;
		
	}
	
	

	
}


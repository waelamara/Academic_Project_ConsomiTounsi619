package tn.esprit.spring.Controller.Charite;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.ManyToOne;
import javax.validation.Valid;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.annotation.JsonIgnore;

import tn.esprit.spring.Controller.GestionUser.LoginController;
import tn.esprit.spring.Model.Commande;
import tn.esprit.spring.Model.User;
import tn.esprit.spring.Model.Charite.Charite;
import tn.esprit.spring.Model.Charite.Events;
import tn.esprit.spring.Service.Charite.ChariteDAO;
import tn.esprit.spring.Service.Charite.EndroitDAO;
import tn.esprit.spring.Service.Charite.EventsDAO;
import tn.esprit.spring.Service.GestionUser.UserService;
import tn.esprit.spring.Service.Panier.CommandeImpl;
import tn.esprit.spring.security.services.UserDetailsImpl;

@Controller(value = "ControllerCharite")
@ELBeanName(value = "ControllerCharite")
@Join(path = "/addcharite", to = "/AddCharite.jsf")
public class ControllerCharite {
	private String typeCharite;
	private float montantPaye;
	private User iduser;
	private Set<Commande> commandeCharite = new HashSet<>() ;
    private Events idevents ;
    
	
	public Set<Commande> getCommandeCharite() {
		return commandeCharite;
	}
	public void setCommandeCharite(Set<Commande> commandeCharite) {
		this.commandeCharite = commandeCharite;
	}
	public User getIduser() {
		return iduser;
	}
	public void setIduser(User iduser) {
		this.iduser = iduser;
	}
	public Events getIdevents() {
		return idevents;
	}
	public void setIdevents(Events idevents) {
		this.idevents = idevents;
	}


	@Autowired
	EventsDAO eventDAO;
	@Autowired
	CommandeImpl commandeDao;
	@Autowired
	ChariteDAO chariteDAO;
	@Autowired
	EndroitDAO endroitDAO;
	@Autowired
	UserService userDAO;
	
	public String getTypeCharite() {
		return typeCharite;
	}
	public void setTypeCharite(String typeCharite) {
		this.typeCharite = typeCharite;
	}
	public float getMontantPaye() {
		return montantPaye;
	}
	public void setMontantPaye(float montantPaye) {
		this.montantPaye = montantPaye;
	}
	public List<Charite> getAllCharite() {
		return chariteDAO.getAllChariteList();
	}
	public List<Charite> getAllChariteUser() {
		
		LoginController.userDetails.getFirstName();
		List<Charite> com=new ArrayList<>();
		//UserDetailsImpl u1 = (UserDetailsImpl) authentication.getPrincipal();
		com = chariteDAO.getCharite(LoginController.userDetails.getId());
		return com;
		
	
	}
	
	/********** Commande *******************/
	
	public String addChariteesCommande(Authentication authentication,Long idCommande,Long idevents,Charite Charite) {
		Events e1 = eventDAO.findOne(idevents);
  		UserDetailsImpl u1 = (UserDetailsImpl) authentication.getPrincipal();		
		User u2= userDAO.findOne(u1.getId());
		int nb = e1.getNbplace();
		int nbP = e1.getNbparticipant();
		float S ;
		
		if ((e1.getNbplace() > 0)&&(u2.getSolde()>Charite.getMontantPaye())
				&&(Charite.getTypeCharite().equals("dons"))) {
			Commande c1= commandeDao.findOne(idCommande);
			Set<Commande> c= new HashSet<Commande>();
			c.add(c1);
			e1.setTitre(e1.getTitre());
			e1.setDateE(e1.getDateE());
			e1.setEndroit(e1.getEndroit());
			e1.setNbplace(nb - 1);
			e1.setNbparticipant(nbP + 1);
			e1.setPublicite(e1.getPublicite());
			e1.setCharite(e1.getCharite());
			e1.setDescription(e1.getDescription());
			e1.setImage(e1.getImage());
			S=u2.getSolde()-c1.getMontant()-Charite.getMontantPaye();
			u2.setSolde(S);
			
			Charite.setCommandeCharite(c);
			eventDAO.saveEvents(e1);
			userDAO.save(u2);
			commandeDao.save(c1);
			chariteDAO.saveCharitee(e1.getId(), u1.getId(),c1.getId(), Charite);
			//eventDAO.sendSms();
			
			/*Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

		    Message message = Message.creator(new PhoneNumber("+21629651973"),
		        new PhoneNumber("+18654261966"), 
		        u1.getFirstName()+" "+u1.getLastName()+" "+"I registered for this event "+" "
		        +e1.getTitre()+" ,"+"the date"+" "+e1.getDateE()
		        +" , "+"donate ordered ID="+" "+c1.getId()+"       "+"thank you so much").create();

		    System.out.println(message.getSid());*/
			
			return "Successful Donated a ordered";

		} 
		
		
		
		else {
			return "insufficient space";

		}
}
	
	/********** money *******************/
	public String addChariteesMoney(Authentication authentication,Long idevents,Charite Charite) {
		Events e1 = eventDAO.findOne(idevents);
  		UserDetailsImpl u1 = (UserDetailsImpl) authentication.getPrincipal();		
		User u2= userDAO.findOne(u1.getId());
		int nb = e1.getNbplace();
		int nbP = e1.getNbparticipant();
		float S ;
		if ((e1.getNbplace() > 0)&&(u2.getSolde()>Charite.getMontantPaye())
				&&(Charite.getTypeCharite().equals("cagnotte"))) {			
			e1.setTitre(e1.getTitre());
			e1.setDateE(e1.getDateE());
			e1.setEndroit(e1.getEndroit());
			e1.setNbplace(nb - 1);
			e1.setNbparticipant(nbP + 1);
			e1.setPublicite(e1.getPublicite());
			e1.setCharite(e1.getCharite());
			e1.setDescription(e1.getDescription());
			e1.setImage(e1.getImage());
			S=u2.getSolde()-Charite.getMontantPaye();
			u2.setSolde(S);
			eventDAO.saveEvents(e1);
			userDAO.save(u2);
			chariteDAO.saveCharite1(e1.getId(), u1.getId(), new Charite(typeCharite, montantPaye));
			/*Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

		    Message message = Message.creator(new PhoneNumber("+21629651973"),
		        new PhoneNumber("+18654261966"), 
		        u1.getFirstName()+" "+u1.getLastName()+" "+"I registered for this event "+" "
		        +e1.getTitre()+" ,"+"the date"+" "+e1.getDateE()
		        +" , "+"donate money"+" "+Charite.getMontantPaye()+"       "+"thank you so much").create();

		    System.out.println(message.getSid());*/
			//eventDAO.sendSms();
			
			
			return "Successful Donate money thank you";

		} 
		
		else if(u2.getSolde()<Charite.getMontantPaye()){
			return "your insufficient balance thank you";
			
		}
		
		
		else {
			return "insufficient space";

		}
}
	
	/*********************charite*******************************/
	
	
public String addCh() {
	
//	Charite c = new Charite();
	//Events e = new Events();
//	Events e1 = eventDAO.findOne(idevents.getId());
	//c.setIdevents(e1);
	//c.setMontantPaye(montantPaye);
	//c.setTypeCharite(typeCharite);
	System.out.println(typeCharite);
	System.out.println(montantPaye);
	Events e1 = eventDAO.findOne(ide);
	System.out.println(ide);
	chariteDAO.saveCharite5(ide, new Charite(typeCharite, montantPaye,e1));
	
	//chariteDAO.saveCharit(new Charite(typeCharite, montantPaye, idevents));
	//chariteDAO.saveCharit2(idevents, new Charite(typeCharite, montantPaye));
		return "/Charity.xhtml?faces-redirect=true";
	}
public static Long ide ;
public String addCh1(Long idevents) {
	
	 ide = idevents;
	chariteDAO.saveCharit2(idevents, new Charite());
	System.out.println(idevents);
	return "/AddCharite.xhtml?faces-redirect=true";
}
	

public String addChariteesMoney1(Long iduser,Long idevents,Charite Charite) {
	Events e1 = eventDAO.findOne(idevents);
	User u2= userDAO.findOne(iduser);
	int nb = e1.getNbplace();
	int nbP = e1.getNbparticipant();
	float S ;
	if ((e1.getNbplace() > 0)&&(u2.getSolde()>Charite.getMontantPaye())
			&&(Charite.getTypeCharite().equals("cagnotte"))) {			
		e1.setTitre(e1.getTitre());
		e1.setDateE(e1.getDateE());
		e1.setEndroit(e1.getEndroit());
		e1.setNbplace(nb - 1);
		e1.setNbparticipant(nbP + 1);
		e1.setPublicite(e1.getPublicite());
		e1.setCharite(e1.getCharite());
		e1.setDescription(e1.getDescription());
		e1.setImage(e1.getImage());
		S=u2.getSolde()-Charite.getMontantPaye();
		u2.setSolde(S);
		eventDAO.saveEvents(e1);
		userDAO.save(u2);
		chariteDAO.saveCharite1(e1.getId(), u2.getId(), new Charite(typeCharite, montantPaye));
		/*Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

	    Message message = Message.creator(new PhoneNumber("+21629651973"),
	        new PhoneNumber("+18654261966"), 
	        u1.getFirstName()+" "+u1.getLastName()+" "+"I registered for this event "+" "
	        +e1.getTitre()+" ,"+"the date"+" "+e1.getDateE()
	        +" , "+"donate money"+" "+Charite.getMontantPaye()+"       "+"thank you so much").create();

	    System.out.println(message.getSid());*/
		//eventDAO.sendSms();
		
		
		return "Successful Donate money thank you";

	} 
	
	else if(u2.getSolde()<Charite.getMontantPaye()){
		return "your insufficient balance thank you";
		
	}
	
	
	else {
		return "insufficient space";

	}
}
/*********delete******************/
public void delete(long Id) {

	chariteDAO.deleteChariteById(Id);

}
	

}

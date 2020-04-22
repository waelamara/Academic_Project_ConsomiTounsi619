package tn.esprit.spring.Controller.Charite;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import Utils.AppConstants;
import tn.esprit.spring.Model.Commande;
import tn.esprit.spring.Model.User;
import tn.esprit.spring.Model.Charite.Charite;
import tn.esprit.spring.Model.Charite.Endroit;
import tn.esprit.spring.Model.Charite.Events;
import tn.esprit.spring.Service.Charite.ChariteDAO;
import tn.esprit.spring.Service.Charite.EndroitDAO;
import tn.esprit.spring.Service.Charite.EventsDAO;
import tn.esprit.spring.Service.GestionUser.UserService;
import tn.esprit.spring.Service.Panier.CommandeImpl;
import tn.esprit.spring.Service.Produit.FileStorageServiceImpl;
import tn.esprit.spring.security.services.UserDetailsImpl;

@Controller
public class ControllerEvents {
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
	@Autowired
	FileStorageServiceImpl fileStorageServiceImpl;
	ObjectMapper objectMapper = new ObjectMapper();

	public static final String ACCOUNT_SID = "AC25eeab7c940f79dd272d5bc2d7337437";
	  public static final String AUTH_TOKEN = "cf00808dd9240106de0943465ae7408e";
	
	public String addEvents(Events e) {
		eventDAO.saveEvents(e);
		return "Successful";
	}
	public List<Events> getAllEvents() {
		return eventDAO.getAllEventsList();
	}
	public String ModifEvents(Events e) {
		eventDAO.upsateEvents(e);
		return "Successful";
	}
	public void delete(long Id) {

		eventDAO.deleteEventsById(Id);

	}
	
	public String addChar( Long idevents, Long iduser, Charite Charite) {
		Events e1 = eventDAO.findOne(idevents);
		User u1= userDAO.findOne(iduser);
		if ((e1.getNbplace() > 0)&&(u1.getSolde()>Charite.getMontantPaye())) {
			float S ;
			int nb = e1.getNbplace();
			int nbP = e1.getNbparticipant();
			e1.setTitre(e1.getTitre());
			e1.setDateE(e1.getDateE());
			e1.setEndroit(e1.getEndroit());
			e1.setNbplace(nb - 1);
			e1.setNbparticipant(nbP + 1);
			e1.setPublicite(e1.getPublicite());
			e1.setCharite(e1.getCharite());
			e1.setDescription(e1.getDescription());
			e1.setImage(e1.getImage());
			S=u1.getSolde()-Charite.getMontantPaye();
			u1.setSolde(S);
			userDAO.save(u1);
			eventDAO.saveEvents(e1);
			chariteDAO.saveCharite(idevents, iduser, Charite);
			return "Successful";

		} 
		else if(u1.getSolde()<Charite.getMontantPaye()){
			return "your insufficient balance";
			
		}
		else {
			return "insufficient space";

		}
		
	}
/***************************/
	public String addCharitees(Authentication authentication,Charite Charite) {
		Events e1 = eventDAO.findOne(Charite.getEvent_id());
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
			chariteDAO.saveCharite1(e1.getId(), u1.getId(), Charite);
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
		if ((e1.getNbplace() > 0)&&(u2.getSolde()>Charite.getMontantPaye())
				&&(Charite.getTypeCharite().equals("dons"))&&(Charite.getCommande_id()!=0)) {
			Commande c1= commandeDao.findOne(Charite.getCommande_id());
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
			
		/*	Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

		    Message message = Message.creator(new PhoneNumber("+21629651973"),
		        new PhoneNumber("+18654261966"), 
		        u1.getFirstName()+" "+u1.getLastName()+" "+"I registered for this event "+" "
		        +e1.getTitre()+" ,"+"the date"+" "+e1.getDateE()
		        +" , "+"donate ordered ID="+" "+c1.getId()+"       "+"thank you so much").create();

		    System.out.println(message.getSid());*/
			
			return "Successful Donated a ordered";

		} 
		else if(u2.getSolde()<Charite.getMontantPaye()){
			return "your insufficient balance thank you";
			
		}
		
		
		else {
			return "insufficient space";

		}
}
	
	public String reserveEndroit( Endroit e) {
		Endroit e2 = endroitDAO.findOne(e.getId());
		Events d1 = eventDAO.findOne(e.getEvent_id());
		String message = "this place is reserved ";
		String message1 = "Successful";
		String message2 = "number of places less than number of places in its event";

		int nbPEndroit = e2.getNbplace();
		int nbPEvent = d1.getNbplace();
		if ((e2.getStatu().equals("disponible")) && (nbPEndroit > nbPEvent)) {
			e2.setNbplace(e2.getNbplace());
			e2.setEmplacement(e2.getEmplacement());
			e2.setEventss(e2.getEventss());
			e2.setStatu("Reservé");
			endroitDAO.saveEndroit(d1.getId(), e2);
			return message1;
		} else if (nbPEndroit < nbPEvent) {
			return message2;
		} else {
			return message ;
		}

	}
	/* affiche les charites pour chaque user*/
	
	public ResponseEntity<?> getAllChariteUser(Authentication authentication,Charite C) {
		List<Charite> com=new ArrayList<>();
		UserDetailsImpl u1 = (UserDetailsImpl) authentication.getPrincipal();
		com = chariteDAO.getCharite(u1.getId());
		return ResponseEntity.ok().body(com);
	}
	
	/* add endroit */
	public Endroit addEndroit( Endroit Endroit) {

		return endroitDAO.saveEndroit1(Endroit);
	}
	/* add events avec publicité */
	public int addPub(Long publicite,Events Events) {

		return eventDAO.saveEvent(publicite, Events);
	}
	

	/* affiche les charites */

	public List<Charite> getAllCharite() {
		return chariteDAO.getAllChariteList();
	}


	/* affiche les endroits */
	public List<Endroit> getAllEndroit() {
	
		return endroitDAO.getAllEndroitList();
	}
	/**************recherche par titre***************/
	public List<Events> findLikeNameM(String titre) {
		return eventDAO.findLikeName(titre);
	}
    

}

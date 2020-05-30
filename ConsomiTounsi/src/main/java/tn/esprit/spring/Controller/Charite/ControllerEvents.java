package tn.esprit.spring.Controller.Charite;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.context.FacesContext;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.servlet.ServletException;
import javax.validation.Valid;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.primefaces.model.file.UploadedFile;
import org.primefaces.model.file.UploadedFiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
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

@Controller(value = "ControllerEvents")
@ELBeanName(value = "ControllerEvents")
@Join(path = "/AddEvent", to = "AddEvent.jsf")
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
	public static final String ACCOUNT_SID = "AC25eeab7c940f79dd272d5bc2d7337437";
	  public static final String AUTH_TOKEN = "cf00808dd9240106de0943465ae7408e";
	
	private Long Id;
	private String titre;
	private Date dateE;
	private int nbplace;
	private int nbparticipant;
	private String description;
	private String image;
	private UploadedFiles files;
	
	
	
	
	public UploadedFiles getFiles() {
		return files;
	}

	public void setFiles(UploadedFiles files) {
		this.files = files;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	

	public Date getDateE() {
		return dateE;
	}

	public void setDateE(Date dateE) {
		this.dateE = dateE;
	}

	public int getNbplace() {
		return nbplace;
	}

	public void setNbplace(int nbplace) {
		this.nbplace = nbplace;
	}

	public int getNbparticipant() {
		return nbparticipant;
	}

	public void setNbparticipant(int nbparticipant) {
		this.nbparticipant = nbparticipant;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String addEvents(Events e) {
		eventDAO.saveEvents(e);
		return "Successful";
	}

	public List<Events> getAllEvents() {
		return eventDAO.getAllEventsList();
	}

	public String modifEvents(Events e) {
		eventDAO.upsateEvents(e);
		return "Successful";

	}
	
	public void delete(long Id) {

		eventDAO.deleteEventsById(Id);

	}
	public List<Events> findLikeNameM(String titre) {
		return eventDAO.findLikeName(titre);
	}
	
	public List<Events> getEventsParDate() {
		return eventDAO.getEventsParDate();
	}
	
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
		
		else if(u2.getSolde()<Charite.getMontantPaye()){
			return "your insufficient balance thank you";
			
		}
		
		
		else {
			return "insufficient space";

		}
}
	
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
	
	public String reserveEndroit( Long idendroit,
			Long ideventss, Endroit e) {
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
	public List<Endroit> getAllEndroit() {
		
		return endroitDAO.getAllEndroitList();
	}
	public int addPub(Long publicite,Events Events) {

		return eventDAO.saveEvent(publicite, Events);
	}
	public Endroit addEndroit(Endroit Endroit) {

		return endroitDAO.saveEndroit1(Endroit);
	}
	//private Events e = new Events(titre, description, dateE, nbplace, nbparticipant, image);
	public String addEv() {
		 DateFormat df=new SimpleDateFormat("dd/MM/yyyy");
	        df.format(dateE);
	        
		
		
	//	eventDAO.saveEvents(new Events(titre, description, dateE, nbplace, nbparticipant,image));
		//eventDAO.saveEvents(new Events(titre, description, dateE, nbplace, nbparticipant,image));
		
eventDAO.saveEventss(new Events(titre, description, dateE, nbplace, nbparticipant), files);
		return "/EventAdmin.xhtml?faces-redirect=true";
	}
	 public String save() {
		 
	       return eventDAO.save();
	    }
	
	 public static Long idev ;
	 public String reserveE(Long ideventss) {
			
		 idev = ideventss;
		endroitDAO.saveEndroit(ideventss, new Endroit());
		System.out.println(ideventss);
		return "/Endroit.xhtml?faces-redirect=true";
	}
    

}

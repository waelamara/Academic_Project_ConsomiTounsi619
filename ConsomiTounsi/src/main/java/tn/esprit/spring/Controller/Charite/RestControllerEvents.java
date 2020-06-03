package tn.esprit.spring.Controller.Charite;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.security.core.Authentication;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.exception.StripeException;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import Utils.AppConstants;

import tn.esprit.spring.Service.Charite.ChariteDAO;
import tn.esprit.spring.Service.Charite.EndroitDAO;
import tn.esprit.spring.Service.Charite.EventsDAO;
import tn.esprit.spring.Service.GestionUser.UserService;
import tn.esprit.spring.Service.Panier.CommandeImpl;
import tn.esprit.spring.Service.Produit.FileStorageServiceImpl;
import tn.esprit.spring.security.services.UserDetailsImpl;
import tn.esprit.spring.Model.Commande;
import tn.esprit.spring.Model.User;
import tn.esprit.spring.Model.Charite.Charite;
import tn.esprit.spring.Model.Charite.Endroit;
import tn.esprit.spring.Model.Charite.Events;

@RestController
@RequestMapping("/event")
public class RestControllerEvents {
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
	  
	/* ajouter charité si vous avez ajouter un charité vous avez participer */
	@PostMapping("/Part/{idevents}/{iduser}")
	@ResponseBody
	public String addChar(@PathVariable(value = "idevents") Long idevents, @PathVariable(value = "iduser") Long iduser,
			@Valid @RequestBody Charite Charite) {
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

	/* ajouter event avec image*/
	//http://localhost:8081/event/addEvent
	@PostMapping("/addEvent")
	@ResponseBody
	public String addEvents(@RequestParam(value = "Events", required = true) String EventsJson,
			@RequestParam(required = true, value = AppConstants.EMPLOYEE_FILE_PARAM) List<MultipartFile> file) 
					throws JsonParseException, JsonMappingException,IOException {
		Events e = objectMapper.readValue(EventsJson, Events.class);

		for (MultipartFile i : file) {
			String fileName = fileStorageServiceImpl.storeFile(i);
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
					.path(AppConstants.DOWNLOAD_PATH).path(fileName).toUriString();
			e.setImage(fileDownloadUri);
			eventDAO.saveEvents(e);

		}
		return "Successful";
	}

	/* affiche les events */
	//http://localhost:8081/event/allEvent
	@RequestMapping(value = "/allEvent")
	public List<Events> getAllEvents() {
		return eventDAO.getAllEventsList();
	}

	/* modifier event */
	//http://localhost:8081/event/editEvent
	@PutMapping("/editEvent")
	@ResponseBody
	public String modifEvents(@RequestParam(value = "Events", required = true) String EventsJson,
			@RequestParam(required = true, value = AppConstants.EMPLOYEE_FILE_PARAM) List<MultipartFile> file) 
			throws JsonParseException, JsonMappingException,IOException {
		Events e = objectMapper.readValue(EventsJson, Events.class);

		for (MultipartFile i : file) {
			String fileName = fileStorageServiceImpl.storeFile(i);
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
					.path(AppConstants.DOWNLOAD_PATH).path(fileName).toUriString();
			e.setImage(fileDownloadUri);
			eventDAO.upsateEvents(e);

		}
		return "Successful";
		
	}

	/* delete un event */
	//http://localhost:8081/event/delete/{id}
	@DeleteMapping("/delete/{id}")

	public void delete(@PathVariable(name = "id") long Id) {

		eventDAO.deleteEventsById(Id);

	}

	/* recherche par titre */
	//http://localhost:8081/event/find/{titre}
	@GetMapping("/find/{titre}")
	public List<Events> findLikeNameM(@PathVariable(value = "titre") String titre) {
		return eventDAO.findLikeName(titre);
	}

	/* affiche les charites */
	//http://localhost:8081/event/allCharite
	@RequestMapping(value = "/allCharite")
	public List<Charite> getAllCharite() {
		return chariteDAO.getAllChariteList();
	}


	/* affiche les endroits */
	//http://localhost:8081/event/allEndroit
	@RequestMapping(value = "/allEndroit")
	public List<Endroit> getAllEndroit() {
	
		return endroitDAO.getAllEndroitList();
	}

	/* reservation Endoit */
	//http://localhost:8081/event/reserve/{ideventss}/{idendroit}
	@PostMapping("/reserve/{ideventss}/{idendroit}")
	@ResponseBody
	public String addChar(@PathVariable(value = "idendroit") Long idendroit,
			@PathVariable(value = "ideventss") Long ideventss, @Valid @RequestBody Endroit e) {
		Endroit e2 = endroitDAO.findOne(idendroit);
		Events d1 = eventDAO.findOne(ideventss);
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
			endroitDAO.saveEndroit(ideventss, e2);
			return message1;
		} else if (nbPEndroit < nbPEvent) {
			return message2;
		} else {
			return message;
		}

	}

	/* add events avec publicité */
	//http://localhost:8081/event/ajouter/{publicite}
	@PostMapping("/ajouter/{publicite}")
	@ResponseBody
	public int addPub(@PathVariable(value = "publicite") Long publicite, @Valid @RequestBody Events Events) {

		return eventDAO.saveEvent(publicite, Events);
	}
	/* add endroit */
	//http://localhost:8081/event/addEndroit
	@PostMapping("/addEndroit")
	@ResponseBody
	public Endroit addEndroit( @Valid @RequestBody Endroit Endroit) {

		return endroitDAO.saveEndroit1(Endroit);
	}
	
	
	/* ajouter charité avec token si vous avez ajouter un charité vous avez participer */
	//http://localhost:8081/event/Participer/{idevents}
	@PostMapping("/Participer/{idevents}")
	@ResponseBody
	public String addCharit(Authentication authentication,@PathVariable(value = "idevents") Long idevents,
			@Valid @RequestBody Charite Charite) {
		Events e1 = eventDAO.findOne(idevents);
		
		UserDetailsImpl u1 = (UserDetailsImpl) authentication.getPrincipal();		
		u1.getId();
		User u2= userDAO.findOne(u1.getId());
		if ((e1.getNbplace() > 0)&&(u2.getSolde()>Charite.getMontantPaye())) {
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
			S=u2.getSolde()-Charite.getMontantPaye();
			u2.setSolde(S);
			userDAO.save(u2);
			eventDAO.saveEvents(e1);
			chariteDAO.saveCharite1(idevents,u1.getId(), Charite);
			return "Successful";

		} 
		else if(u2.getSolde()<Charite.getMontantPaye()){
			return "your insufficient balance";
			
		}
		
		else {
			return "insufficient space";

		}
		
	}
	/**********************************************/
	//http://localhost:8081/event/Participer/{idevents}/{idcommande}
    @PostMapping("/Participer/{idevents}/{idcommande}")
	@ResponseBody
	public String addCharitee(Authentication authentication,@PathVariable(value = "idevents") Long idevents,
			@PathVariable(value = "idcommande") Long idCommande,
			@Valid @RequestBody Charite Charite) {
		Events e1 = eventDAO.findOne(idevents);
		Commande c1= commandeDao.findOne(idCommande);
		Set<Commande> c= new HashSet<Commande>();
		c.add(c1);
		
       
  		UserDetailsImpl u1 = (UserDetailsImpl) authentication.getPrincipal();		
		u1.getId();

		User u2= userDAO.findOne(u1.getId());
		if ((e1.getNbplace() > 0)&&(u2.getSolde()>Charite.getMontantPaye())&&(Charite.getTypeCharite().equals("cagnotte"))) {
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
			S=u2.getSolde()-Charite.getMontantPaye();
			u2.setSolde(S);
			
			userDAO.save(u2);
			eventDAO.saveEvents(e1);
			chariteDAO.saveCharite1(idevents, u1.getId(), Charite);
			return "Successful Donate money thank you";

		} 
		if ((e1.getNbplace() > 0)&&(u2.getSolde()>Charite.getMontantPaye())&&(Charite.getTypeCharite().equals("dons"))) {
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
			S=u2.getSolde()-c1.getMontant()-Charite.getMontantPaye();
			u2.setSolde(S);
			
			Charite.setCommandeCharite(c);
			userDAO.save(u2);
			eventDAO.saveEvents(e1);
			commandeDao.save(c1);
			chariteDAO.saveCharitee(idevents, u1.getId(),c1.getId(), Charite);
			return "Successful Donated a product thank you";

		} 
		else if(u2.getSolde()<Charite.getMontantPaye()){
			return "your insufficient balance thank you";
			
		}
		
		else {
			return "insufficient space";

		}
    }
		
		//http://localhost:8081/event/Participer
	    @PostMapping("/Participer")
		@ResponseBody
		public String addCharitees(Authentication authentication,@Valid @RequestBody Charite Charite) {
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
				Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

			    Message message = Message.creator(new PhoneNumber("+21629651973"),
			        new PhoneNumber("+18654261966"), 
			        u1.getFirstName()+" "+u1.getLastName()+" "+"I registered for this event "+" "
			        +e1.getTitre()+" ,"+"the date"+" "+e1.getDateE()
			        +" , "+"donate money"+" "+Charite.getMontantPaye()+"       "+"thank you so much").create();

			    System.out.println(message.getSid());
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
				
				Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

			    Message message = Message.creator(new PhoneNumber("+21629651973"),
			        new PhoneNumber("+18654261966"), 
			        u1.getFirstName()+" "+u1.getLastName()+" "+"I registered for this event "+" "
			        +e1.getTitre()+" ,"+"the date"+" "+e1.getDateE()
			        +" , "+"donate ordered ID="+" "+c1.getId()+"       "+"thank you so much").create();

			    System.out.println(message.getSid());
				
				return "Successful Donated a ordered";

			} 
			else if(u2.getSolde()<Charite.getMontantPaye()){
				return "your insufficient balance thank you";
				
			}
			
			
			else {
				return "insufficient space";

			}
	}
	    
	    /* reservation Endoit */
		//http://localhost:8081/event/reserve
		@PostMapping("/reserve")
		@ResponseBody
		public String reserveEndroit( @Valid @RequestBody Endroit e) {
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
		//http://localhost:8081/event/allChariteUser
		@RequestMapping(value = "/allChariteUser")
		public ResponseEntity<?> getAllChariteUser(Authentication authentication,@Valid @RequestBody Charite C) {
			List<Charite> com=new ArrayList<>();
			UserDetailsImpl u1 = (UserDetailsImpl) authentication.getPrincipal();
			com = chariteDAO.getCharite(u1.getId());
			return ResponseEntity.ok().body(com);
		}
		
		/* affiche les events de jour */
		//http://localhost:8081/event/EventJour
		@RequestMapping(value = "/EventJour")
		public List<Events> getEventsParDate() {
			return eventDAO.getEventsParDate();
		}
		//http://localhost:8081/event/pay/4/4242424242424242/11/2026/123
		@RequestMapping(value = "/pay/{id}/{carta}/{expMonth}/{expYear}/{cvc}")
		public void getEventsPaie(@PathVariable("id") Long idchar,@PathVariable("carta") String carta,
				@PathVariable("expMonth") int expMonth, @PathVariable("expYear") int expYear,@PathVariable("cvc") String cvc) throws AuthenticationException, InvalidRequestException, CardException, StripeException{
			chariteDAO.Pay(idchar, carta, expMonth, expYear, cvc);
		}

		
		
}

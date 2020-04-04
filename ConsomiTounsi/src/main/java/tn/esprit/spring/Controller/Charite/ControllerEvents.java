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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Utils.AppConstants;
import tn.esprit.spring.DAO.UserDAO;
import tn.esprit.spring.DAO.Charite.ChariteDAO;
import tn.esprit.spring.DAO.Charite.EndroitDAO;
import tn.esprit.spring.DAO.Charite.EventsDAO;
import tn.esprit.spring.Model.Charite.Events;
import tn.esprit.spring.Model.Produit.ImageProduit;
import tn.esprit.spring.Service.Panier.CommandeDAO;
import tn.esprit.spring.Service.Produit.FileStorageServiceImpl;
import tn.esprit.spring.security.services.UserDetailsImpl;
import tn.esprit.spring.Model.Commande;
import tn.esprit.spring.Model.User;
import tn.esprit.spring.Model.Charite.Charite;
import tn.esprit.spring.Model.Charite.Endroit;

@RestController
@RequestMapping("/event")
public class ControllerEvents {
	@Autowired
	EventsDAO eventDAO;
	@Autowired
	CommandeDAO commandeDao;
	@Autowired
	ChariteDAO chariteDAO;
	@Autowired
	EndroitDAO endroitDAO;
	@Autowired
	UserDAO userDAO;
	@Autowired
	FileStorageServiceImpl fileStorageServiceImpl;
	ObjectMapper objectMapper = new ObjectMapper();


	

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

	/* ajouter event avec photo*/
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
	public Events modifEvents(@RequestBody Events Events) {
		return eventDAO.upsateEvents(Events);
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

	/* Participer a un evenement */
	//http://localhost:8081/event/participer/{id}
	@PutMapping("/participer/{id}")
	public String EditEvents(@PathVariable(value = "id") Long idevent, @Valid @RequestBody Events e) {
		Events e1 = eventDAO.findOne(idevent);

		if (e1.getNbplace() > 0) {
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
			eventDAO.saveEvents(e1);
			return "Successful";

		} else {
			return "insufficient space";
			// eventDAO.getAllEventsList();
			// return ResponseEntity.notFound().build();
		}
		// eventDAO.getAllEventsList();

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
			e2.setPrix(e2.getPrix());
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
			return "Successful Donate money";

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
			return "Successful Donated a product";

		} 
		else if(u2.getSolde()<Charite.getMontantPaye()){
			return "your insufficient balance";
			
		}
		
		else {
			return "insufficient space";

		}
		
	}
	

}

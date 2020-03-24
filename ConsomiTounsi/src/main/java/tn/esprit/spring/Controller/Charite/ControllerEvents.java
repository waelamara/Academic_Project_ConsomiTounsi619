package tn.esprit.spring.Controller.Charite;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.DAO.Charite.ChariteDAO;
import tn.esprit.spring.DAO.Charite.EndroitDAO;
import tn.esprit.spring.DAO.Charite.EventsDAO;
import tn.esprit.spring.Model.Charite.Events;
import tn.esprit.spring.Model.Charite.Charite;
import tn.esprit.spring.Model.Charite.Endroit;


@RestController
@RequestMapping("/event")
public class ControllerEvents {
	@Autowired
	EventsDAO eventDAO;
	
	@Autowired
	ChariteDAO chariteDAO;
	@Autowired
	EndroitDAO endroitDAO;
	
	/*@PostMapping("/Paritciper/{id}")
	@ResponseBody
	public ResponseEntity<Charite> addCharite(@PathVariable(value = "id") Long idevents,@RequestBody Charite c,@RequestBody Events e) {
		Events e1 = eventDAO.findOne(idevents);
		if (c == null) {
			return ResponseEntity.notFound().build();
		}
		if (e == null) {
			return ResponseEntity.notFound().build();
		}
	if(chariteDAO.saveCharit(c)==true){
		e1.setTitre(e.getTitre());
		e1.setDateE(e.getDateE());
		e1.setEndroit(e.getEndroit());
		e1.setNbparticipant(e.getNbplace()-1);
		e1.setNbparticipant(e.getNbparticipant()+1);
		e1.setPublicite(e.getPublicite());
		e1.setCharite(e.getCharite());
		eventDAO.saveEvents(e1);
		chariteDAO.saveCharit(c);

		}
		//return chariteDAO.saveCharite(Charite);
		return ResponseEntity.ok().build();
	}*/
	
	/* ajouter charité*/
	@PostMapping("/addChar/{idevents}/{iduser}")
	@ResponseBody
	public int addChar(@PathVariable(value = "idevents") Long idevents,
			@PathVariable(value = "iduser") Long iduser,
			@Valid @RequestBody Charite Charite){
		
		return chariteDAO.saveCharite(idevents,iduser,Charite);
	}
	/* ajouter event*/
	@PostMapping("/addEvent")
	@ResponseBody
	public Events addEvents(@RequestBody Events Events) {
		return eventDAO.saveEvents(Events);
	}
	/* affiche les events*/
	@RequestMapping(value = "/allEvent")
	public List<Events> getAllEvents() {
		return eventDAO.getAllEventsList();
	}
	/* modifier event*/
	@PutMapping("/editEvent")
	@ResponseBody
	public Events modifEvents(@RequestBody Events Events) {
		return eventDAO.upsateEvents(Events);
	}
	/* delete un event*/
	@DeleteMapping("/delete/{id}")

	public void delete(@PathVariable(name = "id") long Id) {

		eventDAO.deleteEventsById(Id);

	}
       /*recherche par titre */
	@GetMapping("/find/{titre}")
	public List<Events> findLikeNameM(@PathVariable(value = "titre") String titre) {
		return eventDAO.findLikeName(titre);
	}
	/* affiche les charites*/
	@RequestMapping(value = "/allCharite")
	public List<Charite> getAllCharite() {
		return chariteDAO.getAllChariteList();
	}
	/* Participer a un evenement*/
	@PutMapping("/participer/{id}")
	public String  EditEvents(@PathVariable(value = "id") Long idevent, @Valid @RequestBody Events e) {
		Events e1 = eventDAO.findOne(idevent);
		/*if (e == null) {
			return "pas d'evenement de cette id ";
		//	return ResponseEntity.notFound().build();
		}*/
		if(e1.getNbplace()>0){
			int nb = e1.getNbplace();
			int nbP = e1.getNbparticipant();
		e1.setTitre(e1.getTitre());
		e1.setDateE(e1.getDateE());
		e1.setEndroit(e1.getEndroit());
		e1.setNbplace(nb-1);
		e1.setNbparticipant(nbP+1);
		e1.setPublicite(e1.getPublicite());
		e1.setCharite(e1.getCharite());
		e1.setDescription(e1.getDescription());
		eventDAO.saveEvents(e1);
		return "Successful";

		}
		else{
			return "insufficient space";
			//eventDAO.getAllEventsList();
		//	return ResponseEntity.notFound().build();
		}
		//eventDAO.getAllEventsList();

	}
	/* affiche les endroits*/
	@RequestMapping(value = "/allEndroit")
	public List<Endroit> getAllEndroit() {
		return endroitDAO.getAllEndroitList();
	}
	/* reservation Endoit*/
	@PostMapping("/reserve/{ideventss}")
	@ResponseBody
	public int addChar(@PathVariable(value = "ideventss") Long ideventss,
			@Valid @RequestBody Endroit Endroit){
		
		return endroitDAO.saveEndroit(ideventss,Endroit);
	}

}

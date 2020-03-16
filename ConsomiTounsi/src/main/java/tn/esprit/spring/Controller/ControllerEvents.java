package tn.esprit.spring.Controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.DAO.EventsDAO;
import tn.esprit.spring.Model.Events;
import tn.esprit.spring.Model.Produit;

@RestController
@RequestMapping("/event")
public class ControllerEvents {
	@Autowired
	EventsDAO eventDAO;

	@PostMapping("/ajouter")
	public Events AjouterProduit(@Valid @RequestBody Events e) {
		return eventDAO.save(e);
	}

	@GetMapping("/afficher")
	public List<Events> getAllEvents() {
		return eventDAO.findAll();
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Events> DeleteEvents(@PathVariable(value = "id") Long idEvents) {
		Events e = eventDAO.findOne(idEvents);
		if (e == null) {
			return ResponseEntity.notFound().build();
		}
		eventDAO.Delete(e);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/edit/{id}")
	public ResponseEntity<Events> EditEvents(@PathVariable(value = "id") Long idEvents,
			@Valid @RequestBody Events e) {
		Events e1 = eventDAO.findOne(idEvents);
		if (e == null) {
			return ResponseEntity.notFound().build();
		}
		Events EditEvents = eventDAO.save(e1);
		return ResponseEntity.ok().body(EditEvents);

	}
	@GetMapping("/recherche/{titre}")
	public List<Events> findLikeNameM(@PathVariable(value = "titre") String titre) {
		return eventDAO.findLikeName(titre);
	}

}

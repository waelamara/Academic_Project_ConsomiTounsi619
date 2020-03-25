package tn.esprit.spring.Controller.Charite;

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

import tn.esprit.spring.DAO.Charite.PubDAO;
import tn.esprit.spring.Model.Charite.Pub;

@RestController
@RequestMapping("/pubEvent")
public class ControllerPub {
	@Autowired
	PubDAO publiciteDAO;
	
	@PostMapping("/ajouter")
	public Pub AjouterPub(@Valid @RequestBody Pub p) {
		return publiciteDAO.save(p);
	}
	
	@GetMapping("/afficher")
	public List<Pub> AfficherPub() {
		return publiciteDAO.findAll();
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Pub> DeletePub(@PathVariable(value = "id") Long idPub) {
		Pub p = publiciteDAO.findOne(idPub);
		if (p == null) {
			return ResponseEntity.notFound().build();
		}
		publiciteDAO.Delete(p);
		return ResponseEntity.ok().build();
	}
	@PutMapping("/edit/{id}")
	public ResponseEntity<Pub> EditProduit(@PathVariable(value = "id") Long idPub,@Valid @RequestBody Pub p) {
		Pub p2 = publiciteDAO.findOne(idPub);
		if (p == null) {
			return ResponseEntity.notFound().build();
		}
		p2.setNom(p.getNom());
		p2.setDateDebut(p.getDateDebut());
		p2.setDateFin(p.getDateFin());
		p2.setImage(p.getImage());
		p2.setEvents(p.getEvents());
		Pub PubliciteModifier = publiciteDAO.save(p2);
		return ResponseEntity.ok().body(PubliciteModifier);

	}

}

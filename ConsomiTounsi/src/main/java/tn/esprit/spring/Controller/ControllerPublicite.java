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

import tn.esprit.spring.DAO.PubliciteDAO;
import tn.esprit.spring.Model.Publicite.Publicite;

@RestController
@RequestMapping("/publicite")
public class ControllerPublicite {
	@Autowired
	PubliciteDAO publiciteDAO;
	
	@PostMapping("/ajouter")
	public Publicite AjouterCategorie(@Valid @RequestBody Publicite p) {
		return publiciteDAO.save(p);
	}
	
	@GetMapping("/afficher")
	public List<Publicite> AfficherCategorie() {
		return publiciteDAO.findAll();
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Publicite> DeleteCategorie(@PathVariable(value = "id") Long idPub) {
		Publicite p = publiciteDAO.findOne(idPub);
		if (p == null) {
			return ResponseEntity.notFound().build();
		}
		publiciteDAO.Delete(p);
		return ResponseEntity.ok().build();
	}
	@PutMapping("/edit/{id}")
	public ResponseEntity<Publicite> EditProduit(@PathVariable(value = "id") Long idPub,@Valid @RequestBody Publicite p) {
		Publicite p2 = publiciteDAO.findOne(idPub);
		if (p == null) {
			return ResponseEntity.notFound().build();
		}
		p2.setCanal(p.getCanal());
		p2.setNom(p.getNom());
		p2.setDateDebut(p.getDateDebut());
		p2.setDateFin(p.getDateFin());
		p2.setNbrInitialVueCible(p.getNbrInitialVueCible());
		p2.setNbrFinalVue(p.getNbrFinalVue());
		p2.setCout(p.getCout());
		Publicite PubliciteModifier = publiciteDAO.save(p2);
		return ResponseEntity.ok().body(PubliciteModifier);

	}
	
}

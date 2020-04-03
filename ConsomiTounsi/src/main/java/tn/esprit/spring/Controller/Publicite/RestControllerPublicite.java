package tn.esprit.spring.Controller.Publicite;

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

import tn.esprit.spring.Model.Publicite.Publicite;
import tn.esprit.spring.Service.Publicite.IPubliciteService;

@RestController
@RequestMapping("/publicite")
public class RestControllerPublicite {
	@Autowired
	IPubliciteService ipubliciteService;

	@PostMapping("/ajouter")
	public Publicite AjouterCategorie(@Valid @RequestBody Publicite p) {
		return ipubliciteService.save(p);
	}

	@GetMapping("/afficher")
	public List<Publicite> AfficherCategorie() {
		return ipubliciteService.findAll();
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> DeleteCategorie(@PathVariable(value = "id") Long idPub) {
		ipubliciteService.Delete(idPub);
		return ResponseEntity.ok("Supprimé avec succès");
	}

	@PutMapping("/edit/{id}")
	public ResponseEntity<Publicite> EditProduit(@PathVariable(value = "id") Long idPub,
			@Valid @RequestBody Publicite p) {
		Publicite PubUpdated = ipubliciteService.Update(p, idPub);
		return ResponseEntity.ok().body(PubUpdated);

	}

}

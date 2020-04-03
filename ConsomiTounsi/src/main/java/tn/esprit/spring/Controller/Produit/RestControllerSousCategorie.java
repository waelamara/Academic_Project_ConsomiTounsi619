package tn.esprit.spring.Controller.Produit;

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

import tn.esprit.spring.Model.Produit.SCategorie;
import tn.esprit.spring.Service.Produit.ICategorieService;
import tn.esprit.spring.Service.Produit.ISousCategorieService;

@RestController
@RequestMapping("/SCategorie")
public class RestControllerSousCategorie {
	@Autowired
	ICategorieService icategorieService;
	@Autowired
	ISousCategorieService isousCategorieService;

	@PostMapping("/ajouter/{idCategorie}")
	public SCategorie AjouterSousCategorie(@PathVariable(value = "idCategorie") Long idCategorie,
			@Valid @RequestBody SCategorie scategorie) {
		return isousCategorieService.save(scategorie, idCategorie);
	}

	@GetMapping("/afficher")
	public List<SCategorie> getAllProduit() {
		return isousCategorieService.findAll();
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> DeleteProduit(@PathVariable(value = "id") Long idScategorie) {
		isousCategorieService.Delete(idScategorie);
		return ResponseEntity.ok("Sous Categorie Supprimer avec succes");
	}

	@PutMapping("/edit/{id}/{idCategorie}")
	public SCategorie EditProduit(@PathVariable(value = "id") Long idScategorie,
			@PathVariable(value = "idCategorie") Long idCategorie, @Valid @RequestBody SCategorie sc) {
		return isousCategorieService.Update(sc, idScategorie, idCategorie);
	}

}

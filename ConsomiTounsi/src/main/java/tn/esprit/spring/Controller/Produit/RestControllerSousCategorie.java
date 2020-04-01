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

import tn.esprit.spring.Model.Produit.Categorie;
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
	public ResponseEntity<SCategorie> AjouterSousCategorie(@PathVariable(value = "idCategorie") Long idCategorie,
			@Valid @RequestBody SCategorie sc) {
		Categorie c = icategorieService.findOne(idCategorie);
		sc.setIdCategorie(c);
		isousCategorieService.save(sc);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/afficher")
	public List<SCategorie> getAllProduit() {
		return isousCategorieService.findAll();
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<SCategorie> DeleteProduit(@PathVariable(value = "id") Long idScategorie) {
		SCategorie sc = isousCategorieService.findOne(idScategorie);
		if (sc == null) {
			return ResponseEntity.notFound().build();
		}
		isousCategorieService.Delete(sc);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/edit/{id}/{idCategorie}")
	public ResponseEntity<SCategorie> EditProduit(@PathVariable(value = "id") Long idScategorie,
			@PathVariable(value = "idCategorie") Long idCategorie, @Valid @RequestBody SCategorie sc) {
		SCategorie sc2 = isousCategorieService.findOne(idScategorie);
		Categorie c = icategorieService.findOne(idCategorie);
		if (sc2 == null) {
			return ResponseEntity.notFound().build();
		}
		sc2.setNomSCategorie(sc.getNomSCategorie());
		sc2.setIdCategorie(c);
		isousCategorieService.save(sc2);
		return ResponseEntity.ok().build();

	}

}

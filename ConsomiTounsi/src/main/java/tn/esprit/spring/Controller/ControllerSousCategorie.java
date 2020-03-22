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

import tn.esprit.spring.DAO.CategorieDAO;
import tn.esprit.spring.DAO.SousCategorieDAO;
import tn.esprit.spring.Model.GestionProduit.Categorie;
import tn.esprit.spring.Model.GestionProduit.SCategorie;

@RestController
@RequestMapping("/SCategorie")
public class ControllerSousCategorie {
	@Autowired
	CategorieDAO categorieDAO;
	@Autowired
	SousCategorieDAO sousCategorieDAO;

	@PostMapping("/ajouter/{idCategorie}")
	public ResponseEntity<SCategorie> AjouterSousCategorie(@PathVariable(value = "idCategorie") Long idCategorie,
			@Valid @RequestBody SCategorie sc) {
		Categorie c = categorieDAO.findOne(idCategorie);
		sc.setIdCategorie(c);
		sousCategorieDAO.save(sc);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/afficher")
	public List<SCategorie> getAllProduit() {
		return sousCategorieDAO.findAll();
	}
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<SCategorie> DeleteProduit(@PathVariable(value = "id") Long idScategorie) {
		SCategorie sc = sousCategorieDAO.findOne(idScategorie);
		if (sc == null) {
			return ResponseEntity.notFound().build();
		}
		sousCategorieDAO.Delete(sc);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/edit/{id}/{idCategorie}")
	public ResponseEntity<SCategorie> EditProduit(@PathVariable(value = "id") Long idScategorie,
			@PathVariable(value = "idCategorie") Long idCategorie, @Valid @RequestBody SCategorie sc) {
		SCategorie sc2 = sousCategorieDAO.findOne(idScategorie);
		Categorie c = categorieDAO.findOne(idCategorie);
		if (sc2 == null) {
			return ResponseEntity.notFound().build();
		}
		sc2.setNomSCategorie(sc.getNomSCategorie());
		sc2.setIdCategorie(c);
		sousCategorieDAO.save(sc2);
		return ResponseEntity.ok().build();

	}
	

}

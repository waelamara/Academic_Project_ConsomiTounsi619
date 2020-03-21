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
import tn.esprit.spring.Model.GestionProduit.Categorie;



@RestController
@RequestMapping("/categorie")
public class ControllerCategorie {
	@Autowired
	CategorieDAO categorieDAO;
	
	
	@PostMapping("/ajouter")
	public Categorie AjouterCategorie(@Valid @RequestBody Categorie c) {
		return categorieDAO.save(c);
	}
	
	@GetMapping("/afficher")
	public List<Categorie> AfficherCategorie() {
		return categorieDAO.findAll();
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Categorie> DeleteCategorie(@PathVariable(value = "id") Long idCategorie) {
		Categorie c = categorieDAO.findOne(idCategorie);
		if (c == null) {
			return ResponseEntity.notFound().build();
		}
		categorieDAO.Delete(c);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/edit/{id}")
	public ResponseEntity<Categorie> EditProduit(@PathVariable(value = "id") Long idCategorie,@Valid @RequestBody Categorie c) {
		Categorie c2 = categorieDAO.findOne(idCategorie);
		if (c == null) {
			return ResponseEntity.notFound().build();
		}
		c2.setNomCategorie(c.getNomCategorie());
		Categorie CategorieModifier = categorieDAO.save(c2);
		return ResponseEntity.ok().body(CategorieModifier);

	}
}

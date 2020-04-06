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

import tn.esprit.spring.Model.Produit.SsCategorie;
import tn.esprit.spring.Service.Produit.ISousCategorieService;
import tn.esprit.spring.Service.Produit.ISousSousCategorieService;

@RestController
@RequestMapping("/SsCategorie")
public class RestControllerSousSousCategorie {
	@Autowired
	ISousCategorieService isousCategorieService;
	@Autowired
	ISousSousCategorieService isousSousCategorieService;

	//http://localhost:8081/SsCategorie/ajouter/3
	//{"nomSsCategorie":"chaussures"}
	@PostMapping("/ajouter/{idSCategorie}")
	public SsCategorie AjouterSousCategorie(@PathVariable(value = "idSCategorie") Long idSCategorie,
			@Valid @RequestBody SsCategorie ssc) {
		return isousSousCategorieService.save(ssc, idSCategorie);
	}

	//http://localhost:8081/SsCategorie/afficher
	@GetMapping("/afficher")
	public List<SsCategorie> getAllProduit() {
		return isousSousCategorieService.findAll();
	}

	//http://localhost:8081/SsCategorie/delete/4
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> DeleteProduit(@PathVariable(value = "id") Long idSscategorie) {
		isousSousCategorieService.Delete(idSscategorie);
		return ResponseEntity.ok("Sous-sous-Categorie Supprimer avec succes");
	}

	//http://localhost:8081/SsCategorie/edit/3/1
	//{"nomSsCategorie":"souris"}
	@PutMapping("/edit/{id}/{idSCategorie}")
	public SsCategorie EditProduit(@PathVariable(value = "id") Long idSscategorie,
			@PathVariable(value = "idSCategorie") Long idSCategorie, @Valid @RequestBody SsCategorie ssc) {
		return isousSousCategorieService.Update(ssc, idSscategorie, idSCategorie);

	}

}

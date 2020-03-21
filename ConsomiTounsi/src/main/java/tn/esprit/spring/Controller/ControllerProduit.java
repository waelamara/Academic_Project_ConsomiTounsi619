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

import tn.esprit.spring.DAO.ProduitDAO;
import tn.esprit.spring.Model.GestionProduit.Produit;

@RestController
@RequestMapping("/produit")
public class ControllerProduit {
	@Autowired
	ProduitDAO produitDAO;

	@PostMapping("/ajouter")
	public Produit AjouterProduit(@Valid @RequestBody Produit p) {
		return produitDAO.save(p);
	}

	@GetMapping("/afficher")
	public List<Produit> getAllProduit() {
		return produitDAO.findAll();
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Produit> DeleteProduit(@PathVariable(value = "id") Long idProduit) {
		Produit p = produitDAO.findOne(idProduit);
		if (p == null) {
			return ResponseEntity.notFound().build();
		}
		produitDAO.Delete(p);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/edit/{id}")
	public ResponseEntity<Produit> EditProduit(@PathVariable(value = "id") Long idProduit, @Valid @RequestBody Produit p) {
		Produit p2 = produitDAO.findOne(idProduit);
		if (p == null) {
			return ResponseEntity.notFound().build();
		}
//		p2.setNomProduit(p.getNomProduit());
//		p2.setPrix(p.getPrix());
//		p2.setDescription(p.getDescription());
		Produit EditProduit = produitDAO.save(p2);
		return ResponseEntity.ok().body(EditProduit);

	}

	@GetMapping("/recherche/{nom}")
	public List<Produit> findLikeNameM(@PathVariable(value = "nom") String name) {
		return produitDAO.findLikeName(name);
	}

}

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
import tn.esprit.spring.DAO.SousSousCategorieDAO;
import tn.esprit.spring.Model.GestionProduit.Produit;
import tn.esprit.spring.Model.GestionProduit.SsCategorie;

@RestController
@RequestMapping("/produit")
public class ControllerProduit {
	@Autowired
	ProduitDAO produitDAO;
	@Autowired
	SousSousCategorieDAO sousSousCategorieDAO;

	@PostMapping("/ajouter/{idSsCategorie}")
	public Produit AjouterProduit(@PathVariable(value = "idSsCategorie") Long idSsCategorie,
			@Valid @RequestBody Produit p) {
		SsCategorie ssc = sousSousCategorieDAO.findOne(idSsCategorie);
		p.setIdSsCategorie(ssc);
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

	@PutMapping("/edit/{id}/{idSsCategorie}")
	public ResponseEntity<Produit> EditProduit(@PathVariable(value = "idSsCategorie") Long idSsCategorie,
			@PathVariable(value = "id") Long idProduit, @Valid @RequestBody Produit p) {
		Produit p2 = produitDAO.findOne(idProduit);
		SsCategorie ssc = sousSousCategorieDAO.findOne(idSsCategorie);
		if (p == null) {
			return ResponseEntity.notFound().build();
		}
		p2.setNomProduit(p.getNomProduit());
		p2.setPrix(p.getPrix());
		p2.setDescription(p.getDescription());
		p2.setBarcode(p.getBarcode());
		p2.setPoids(p.getPoids());
		p2.setPrixAchat(p.getPrixAchat());
		p2.setIdSsCategorie(ssc);
		produitDAO.save(p2);
		return ResponseEntity.ok().build();

	}

	@GetMapping("/recherche/{nom}")
	public List<Produit> findLikeNameM(@PathVariable(value = "nom") String name) {
		return produitDAO.findLikeName(name);
	}

}

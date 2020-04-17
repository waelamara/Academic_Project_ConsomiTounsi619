package tn.esprit.spring.Controller.Livraison;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import tn.esprit.spring.Model.Commande;
import tn.esprit.spring.Model.Livraison;
import tn.esprit.spring.Model.Livreur;
import tn.esprit.spring.Model.Produit.Produit;
import tn.esprit.spring.Repository.LigneCommandeRepository;
import tn.esprit.spring.Repository.LivraisonRepository;
import tn.esprit.spring.Repository.Produit.ProduitRepository;
import tn.esprit.spring.Service.Livraison.LivraisonService;
import tn.esprit.spring.Service.Livreur.LivreurService;
import tn.esprit.spring.Service.Panier.CommandeDAO;
import tn.esprit.spring.Service.Produit.ProduitServiceImpl;

@RestController
@RequestMapping("/livraison")
public class LivraisonContoller {

	@Autowired
	LivraisonService livraisonService;

	@Autowired
	CommandeDAO CommandeDAO;

	@Autowired
	LivreurService LivreurService;

	@Autowired
	LivraisonRepository LivraisonRepository;

	@Autowired
	ProduitServiceImpl ProduitServiceImpl;
	
	@Autowired
	LigneCommandeRepository LigneCommandeRepository;
	
	@Autowired
	ProduitRepository ProduitRepository;

	
	

	/* Enregistrer un livreur */
	@PostMapping("/ajout")

	public Livraison createLivreur(@Valid @RequestBody Livraison liv) {
		if ((liv.getCommande_id() != 0) && (liv.getLivreur_id() != 0)) {
			Commande c = CommandeDAO.findOne(liv.getCommande_id());
			liv.setCommande(c);
			Livreur L = LivreurService.findOne(liv.getLivreur_id());
			liv.setLivreur(L);
			return livraisonService.save(liv);
		} else
			return null;
	}

	/* get all employees */
	@GetMapping("/affichall")
	public List<Livraison> getAllLivreur() {

		return livraisonService.findall();

	}

	@PutMapping("/modifier")

	public Livraison updateLiv(@RequestBody Livraison liv) {
		return livraisonService.updateLiv(liv);
	}

	@DeleteMapping("/delete/{id}")

	public void delete(@PathVariable(value = "id") long id) {
		livraisonService.delete(id);
	}

	@GetMapping("/facture/{id}")
	public float CalculFacture(@PathVariable(value = "id") long id) {
		float Totalpoid = 0;
		Livraison Liv1 = livraisonService.findOne(id);
		Commande commande = Liv1.getCommande();
		long id_commande = commande.getId();
		Produit P = new Produit();

		List<String> Produits = LivraisonRepository.ListProduit_idcommande(id_commande);
		for (String i : Produits) {
            P=ProduitRepository.findById(Long.valueOf(i)).get();
      
			Totalpoid = Totalpoid +P.getPoids();

		}
		float PrixpoidTotal = Totalpoid * 2;
		float prixliv = 10;
		float fraistotalliv = prixliv + PrixpoidTotal;
		System.out.println("aaaaaaaaaaaaaaaaa" + fraistotalliv);
		return fraistotalliv;
		
	}

}

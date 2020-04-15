package tn.esprit.spring.Controller.Panier;

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


import tn.esprit.spring.Model.Commande;
import tn.esprit.spring.Model.lignecommandeproduit;
import tn.esprit.spring.Model.Produit.Produit;
import tn.esprit.spring.Repository.LigneCommandeRepository;
import tn.esprit.spring.Service.Panier.CommandeDAO;

@RestController
@RequestMapping("/commande")
public class RestControllerCommande {
	@Autowired
	CommandeDAO commandeDao;
	
	@PostMapping("/ajouter")
	public Commande AjouterCommande(@Valid @RequestBody Commande c)
	{
		return commandeDao.save(c);
	}
	@GetMapping("/afficher")
	public List<Commande> getAllCommande() {
		return commandeDao.findAll();
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Commande> DeleteCommande(@PathVariable(value = "id") Long idCommande) {
		Commande c =commandeDao.findOne(idCommande);
		if ( c == null) {
			return ResponseEntity.notFound().build();
		}
		commandeDao.Delete(c);
		return ResponseEntity.ok().build();
	}
	@GetMapping("rechercheparcode/{code}")
	public List<Commande>Commandeparcode(@PathVariable(value = "code") long code) {
	
		return commandeDao.Commandeparcode(code);
	}
	 

	
	@GetMapping("/recherchetype/{type}")
	public List<Commande> CommandeparType(@PathVariable(value = "type") String type) {
		return commandeDao.CommandeparType(type);
	}
	@GetMapping("/recherche/{idClient}")
	public List<Commande> CommandeparClient(@PathVariable(value = "idClient") int id) {
		return commandeDao.CommandeparClient(id);
	}
	@PutMapping("/payerenligne/{idCommande}/{idClient}")
	public void PayerEnLigne(@PathVariable(value = "idCommande")int idCommande,@PathVariable(value = "idClient") int id)
	{
		commandeDao.PayerEnLigne( idCommande,id);
	}
	@PutMapping("/payerenligne/{idCommande}/{idClient}/{code}")
	public void PayerEnLigne(@PathVariable(value = "idCommande")int idCommande,@PathVariable(value = "idClient") int id,@PathVariable(value = "code") String code)
	{
		if(code==null)
		{
			commandeDao.PayerEnLigne( idCommande,id);
		}
	     
	     
		commandeDao.PayerEnLigne(idCommande,id,code);
	}
	@PutMapping("/payerporteaporte/{idCommande}/{idClient}")
	public void PayerPorteaPorte(@PathVariable(value = "idCommande")int idCommande,@PathVariable(value = "idClient") int id)
	{
		
		commandeDao.PayerPorteaPorte(idCommande,id);
	}
	@GetMapping("/ParMois")
	public List<Object[]> NumCommadeParMOIS()
	{
		return commandeDao.NumCommadeParMOIS();
	}
	
	

}

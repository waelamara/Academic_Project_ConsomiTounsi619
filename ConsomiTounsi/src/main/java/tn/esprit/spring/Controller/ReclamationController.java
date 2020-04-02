package tn.esprit.spring.Controller;

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
import tn.esprit.spring.Model.reclamation;
import tn.esprit.spring.Service.Reclamation.ReclamationService;
import tn.esprit.spring.Service.Panier.CommandeDAO;

@RestController
@RequestMapping("/Reclamation")
public class ReclamationController {
	
	@Autowired
	ReclamationService ReclamationService;
	
	@Autowired
	CommandeDAO CommandeDAO;
	

	/*Enregistrer une reclamation*/
	
@PostMapping("/ajout/{user_id}")
	
	public reclamation create(@PathVariable(value = "user_id") Long user_id, @Valid @RequestBody reclamation rec )
	{ 
	
	if(rec.getCommande_id()!=0){
		Commande c = CommandeDAO.findOne(rec.getCommande_id());
		rec.setCommande(c);
		return ReclamationService.save(rec, user_id);
	}
	else
		
        
		return ReclamationService.save(rec, user_id);
	}
	
	/*get all reclamation*/
	@GetMapping("/affichall")
	public List<reclamation> getAllrec(){
		
		return ReclamationService.findall();
		
	}
	
	
  @PutMapping("/modifier")
	
	public reclamation updaterec(@RequestBody reclamation rec)
	{
		return ReclamationService.updateLiv(rec );
	}
  
  @DeleteMapping("/delete/{id}")
	
	public void  delete(@PathVariable(value="id") long id)
	{
	  ReclamationService.delete(id);
	}
	
	
	


}

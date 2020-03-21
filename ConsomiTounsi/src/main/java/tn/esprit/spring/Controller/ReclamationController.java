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
import tn.esprit.spring.DAO.ReclamationDAO;
import tn.esprit.spring.Model.reclamation;

@RestController
@RequestMapping("/Reclamation")
public class ReclamationController {
	
	@Autowired
	ReclamationDAO ReclamationDAO;
	

	/*Enregistrer une reclamation*/
	
	@PostMapping("/ajout")
	
	public reclamation createLivreur(@Valid @RequestBody reclamation rec)
	{
		return ReclamationDAO.save(rec);
	}
	
	/*get all employees*/
	@GetMapping("/affichall")
	public List<reclamation> getAllLivreur(){
		
		return ReclamationDAO.findall();
		
	}
	
	
  @PutMapping("/modifier")
	
	public reclamation updateLiv(@RequestBody reclamation rec)
	{
		return ReclamationDAO.updateLiv(rec );
	}
  
  @DeleteMapping("/delete/{id}")
	
	public void  delete(@PathVariable(value="id") long id)
	{
	  ReclamationDAO.delete(id);
	}
	
	
	


}

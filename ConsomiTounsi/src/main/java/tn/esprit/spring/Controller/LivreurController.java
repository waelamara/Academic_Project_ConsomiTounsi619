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

import tn.esprit.spring.DAO.LivreurDAO;
import tn.esprit.spring.Model.Livreur;

@RestController
@RequestMapping("/livreur")
public class LivreurController {
	
	@Autowired
	LivreurDAO livreurDAO;
	
	/*Enregistrer un livreur*/
	@PostMapping("/ajout")
	
	public Livreur createLivreur(@Valid @RequestBody Livreur liv)
	{
		return livreurDAO.save(liv);
	}
	
	/*get all employees*/
	@GetMapping("/affichall")
	public List<Livreur> getAllLivreur(){
		
		return livreurDAO.findall();
		
	}
	
	
  @PutMapping("/modifier")
	
	public Livreur updateLiv(@RequestBody Livreur liv)
	{
		return livreurDAO.updateLiv(liv);
	}
  
  @DeleteMapping("/delete/{id}")
	
	public void  delete(@PathVariable(value="id") long id)
	{
		 livreurDAO.delete(id);
	}
	
	
	

}

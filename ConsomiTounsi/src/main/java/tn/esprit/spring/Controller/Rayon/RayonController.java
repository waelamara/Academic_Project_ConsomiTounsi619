package tn.esprit.spring.Controller.Rayon;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.spring.DAO.Rayon.RayonDAO;
import tn.esprit.spring.Model.Rayon.Rayon;

@RestController
@RequestMapping("/rayon")
public class RayonController {
	
	@Autowired 
	RayonDAO rayonDAO;
	
///////////////////////////////////////////////////////////////////////////////	
	@PostMapping("/addrayon")
	@ResponseBody
	public Rayon addrayon(@RequestBody Rayon rayon){
		return rayonDAO.saveRayon(rayon);
		
	}
	
////////////////////////////////////////////////////////////////////////	
	@RequestMapping(value = "/allrayon")
	public List<Rayon> getAllRayon() {
		return rayonDAO.getAllRayon();
	}
///////////////////////////////////////////////////////////////////////////
	
	@PutMapping("/editrayon")
	@ResponseBody
	public Rayon editrayon(@RequestBody Rayon rayon) {
		return rayonDAO.updateRayon(rayon);
	}
	
////////////////////////////////////////////////////////////////////////////////	
	
	@DeleteMapping("/deleterayon/{Idrayon}")
	public void deleterayon(@PathVariable(name ="Idrayon") long Idrayon) {

		rayonDAO.deleteRayonById(Idrayon);

	}
	
//////////////////////////////////////////////////////////////////////////////
	@GetMapping("/findrayon/{nom_rayon}")
	@ResponseBody
	public List<Rayon> rechercherrayon(@PathVariable(value ="nom_rayon") String nom_rayon){
		return rayonDAO.findRayonbyName(nom_rayon);
	}
	
}

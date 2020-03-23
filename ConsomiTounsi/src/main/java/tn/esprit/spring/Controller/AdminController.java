package tn.esprit.spring.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.DAO.ReclamationDAO;
import tn.esprit.spring.Model.reclamation;

@RestController
@RequestMapping()
public class AdminController {
	
	@Autowired
	ReclamationDAO ReclamationDAO;

@PutMapping("reclamation/traiter/{rec_id}")
	
	public reclamation traiterrec(@PathVariable(value="rec_id") long rec_id,@Valid @RequestBody reclamation rec)
	{   
	    reclamation rec1 = ReclamationDAO.findbyid(rec_id);
	    rec1.setTraiter(true);
	    rec1.setReponse(rec.getReponse());
		return ReclamationDAO.traiter(rec1);
	}
}

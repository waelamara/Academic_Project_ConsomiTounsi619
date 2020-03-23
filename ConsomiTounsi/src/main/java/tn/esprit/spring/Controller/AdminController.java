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

@PutMapping("reclamation/rembourser/{rec_id}")
	
	public reclamation Rembourser_rec(@PathVariable(value="rec_id") long rec_id,@Valid @RequestBody reclamation rec)
	{   
	    reclamation rec1 = ReclamationDAO.findbyid(rec_id);
	    rec1.setTraiter(true);
	    rec1.setEtat("Remboursé");
	    rec1.setReponse(rec.getReponse());
		return ReclamationDAO.traiter(rec1);
	}

@PutMapping("reclamation/Echange/{rec_id}")

public reclamation Echange_rec(@PathVariable(value="rec_id") long rec_id,@Valid @RequestBody reclamation rec)
{   
    reclamation rec1 = ReclamationDAO.findbyid(rec_id);
    rec1.setTraiter(true);
    rec1.setEtat("Echange");
    rec1.setReponse(rec.getReponse());
	return ReclamationDAO.traiter(rec1);
}

@PutMapping("reclamation/reparation/{rec_id}")

public reclamation reparation_rec(@PathVariable(value="rec_id") long rec_id,@Valid @RequestBody reclamation rec)
{   
    reclamation rec1 = ReclamationDAO.findbyid(rec_id);
    rec1.setTraiter(true);
    rec1.setEtat("Reparation");
    rec1.setReponse(rec.getReponse());
	return ReclamationDAO.traiter(rec1);
}
}

package tn.esprit.spring.Controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.Model.reclamation;
import tn.esprit.spring.Service.Reclamation.ReclamationService;

@RestController
@RequestMapping()
public class AdminController {

	@Autowired
	ReclamationService ReclamationService;
	private int A;
	private int B;
	private int res;

	SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

	/***** Oussama_reclamation ******/
	@PutMapping("reclamation/rembourser/{rec_id}")

	public reclamation Rembourser_rec(@PathVariable(value = "rec_id") long rec_id,
			@Valid @RequestBody reclamation rec) {
		reclamation rec1 = ReclamationService.findbyid(rec_id);

		//
		// try {
		// A = sdf.parse(rec1.getCommande().getDate().toString());
		// B = sdf.parse(LocalDate.now().toString());
		// } catch (ParseException e) {
		//
		// }

		// long diff = A.getTime() - B.getTime();
		// res = (diff / (1000 * 60 * 60 * 24));

		// if (res < 15) {
		/* x */
		// } else
		// return null;
	
		A = rec1.getCommande().getDate().getDayOfYear();
		int yearA = rec1.getCommande().getDate().getYear();
		int yearB = LocalDate.now().getYear();
		int diffY = yearB-yearA;
		B = LocalDate.now().getDayOfYear();
		res=B-A;

    
    if ((res < 15) && (diffY<1)) {
		rec1.setTraiter(true);
		rec1.setEtat("Remboursé");
		rec1.setReponse(rec.getReponse());
		return ReclamationService.traiter(rec1);
	    }
	    else
	    	return null;

	}

	@PutMapping("reclamation/Echange/{rec_id}")

	public reclamation Echange_rec(@PathVariable(value = "rec_id") long rec_id, @Valid @RequestBody reclamation rec) {
		reclamation rec1 = ReclamationService.findbyid(rec_id);
		
		A = rec1.getCommande().getDate().getDayOfYear();
		int yearA = rec1.getCommande().getDate().getYear();
		int yearB = LocalDate.now().getYear();
		int diffY = yearB-yearA;
		B = LocalDate.now().getDayOfYear();
		res=B-A;

    
    if ((res < 15) && (diffY<1)) {
		rec1.setTraiter(true);
		rec1.setEtat("Echange");
		rec1.setReponse(rec.getReponse());
		return ReclamationService.traiter(rec1);}
    else
    	return null;
	}

	@PutMapping("reclamation/reparation/{rec_id}")

	public reclamation reparation_rec(@PathVariable(value = "rec_id") long rec_id,
			@Valid @RequestBody reclamation rec) {
		reclamation rec1 = ReclamationService.findbyid(rec_id);
		A = rec1.getCommande().getDate().getDayOfYear();
		int yearA = rec1.getCommande().getDate().getYear();
		int yearB = LocalDate.now().getYear();
		int diffY = yearB-yearA;
		B = LocalDate.now().getDayOfYear();
		res=B-A;

    
    if ((res < 15) && (diffY<1)) {
		rec1.setTraiter(true);
		rec1.setEtat("Reparation");
		rec1.setReponse(rec.getReponse());
		return ReclamationService.traiter(rec1);}
	    else
	    	return null;
	}
	
	
}

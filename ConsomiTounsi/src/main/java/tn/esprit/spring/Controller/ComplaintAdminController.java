package tn.esprit.spring.Controller;

import java.util.List;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import tn.esprit.spring.Model.reclamation;
import tn.esprit.spring.Service.Reclamation.ReclamationService;


@Controller(value = "ComplaintAdminController")
@ELBeanName(value = "ComplaintAdminController")
@Join(path = "/showComplaint", to = "/ComplaintAdmin.jsf")
public class ComplaintAdminController {
	
	@Autowired
	ReclamationService ReclamationService;
	
	/*get all reclamation*/
	public List<reclamation> getAllrec(){
		
		return ReclamationService.findall();
		
	}
	

}

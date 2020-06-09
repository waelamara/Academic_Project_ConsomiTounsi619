package tn.esprit.spring.Controller;

import java.util.List;

import javax.transaction.Transactional;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.Model.reclamation;
import tn.esprit.spring.Repository.reclamationRepository;
import tn.esprit.spring.Service.Reclamation.ReclamationService;


@Controller(value = "ComplaintAdminController")
@ELBeanName(value = "ComplaintAdminController")
@Join(path = "/showComplaint", to = "/ReclamationAdmin.jsf")
public class ComplaintAdminController {
	
	@Autowired
	ReclamationService ReclamationService;
	@Autowired
	reclamationRepository r;
	
	/*get all reclamation*/
	public List<reclamation> getAllrec(){
		
		return ReclamationService.findall();
		
	}
	/*Rembourser reclamation*/
	@Transactional
	public String rembourserReclamation(long id){
		String navigateTo = "/ReclamationAdmin.xhtml"; 
		r.ChangetEtat("Remboursement", id);
		return navigateTo;
		
		
	}
	/*Echange reclamation*/
	@Transactional
	public String echangeReclamation(long id){
		String navigateTo = "/ReclamationAdmin.xhtml"; 
		r.ChangetEtat("Echange", id);
		return navigateTo;
		
		
	}
	/*Reparer reclamation*/
	@Transactional
	public String reparerReclamation(long id){
		String navigateTo = "/ReclamationAdmin.xhtml"; 
		r.ChangetEtat("Réparation", id);
		return navigateTo;
		
		
	}
	

}

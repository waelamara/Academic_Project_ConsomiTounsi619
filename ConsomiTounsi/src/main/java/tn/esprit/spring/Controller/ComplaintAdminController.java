package tn.esprit.spring.Controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
	private int A;
	private int B;
	private int res;

	SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	
	public int getA() {
		return A;
	}
	public void setA(int a) {
		A = a;
	}
	public int getB() {
		return B;
	}
	public void setB(int b) {
		B = b;
	}
	public int getRes() {
		return res;
	}
	public void setRes(int res) {
		this.res = res;
	}
	/*get all reclamation*/
	public List<reclamation> getAllrec(){
		
		return ReclamationService.findall();
		
	}
	/*Rembourser reclamation*/
	@Transactional
	public String rembourserReclamation(long id){
		String navigateTo = "/ReclamationAdmin.xhtml"; 
		reclamation rec1 = ReclamationService.findbyid(id);
		A = rec1.getCommande().getDate().getDayOfYear();
		int yearA = rec1.getCommande().getDate().getYear();
		int yearB = LocalDate.now().getYear();
		int diffY = yearB-yearA;
		B = LocalDate.now().getDayOfYear();
		res=B-A;
		if ((res < 15) && (diffY<1)) {
		r.ChangetEtat("Remboursement", id);
		}
		else{
			r.ChangetEtat("En_attente", id);
			
		}
		return navigateTo;
		
		
	}
	/*Echange reclamation*/
	@Transactional
	public String echangeReclamation(long id){
		String navigateTo = "/ReclamationAdmin.xhtml"; 
		reclamation rec1 = ReclamationService.findbyid(id);
		A = rec1.getCommande().getDate().getDayOfYear();
		int yearA = rec1.getCommande().getDate().getYear();
		int yearB = LocalDate.now().getYear();
		int diffY = yearB-yearA;
		B = LocalDate.now().getDayOfYear();
		res=B-A;
		if ((res < 15) && (diffY<1)) {
		r.ChangetEtat("Echange", id);
		}
		else{
			r.ChangetEtat("En_attente", id);
			
		}
		
		return navigateTo;
		
		
	}
	/*Reparer reclamation*/
	@Transactional
	public String reparerReclamation(long id){
		String navigateTo = "/ReclamationAdmin.xhtml"; 
		reclamation rec1 = ReclamationService.findbyid(id);
		A = rec1.getCommande().getDate().getDayOfYear();
		int yearA = rec1.getCommande().getDate().getYear();
		int yearB = LocalDate.now().getYear();
		int diffY = yearB-yearA;
		B = LocalDate.now().getDayOfYear();
		res=B-A;
		if ((res < 15) && (diffY<1)) {
		r.ChangetEtat("Réparation", id);
		}
		else{
			r.ChangetEtat("En_attente", id);
			
		}
		
		return navigateTo;
		
		
	}
	
	

}

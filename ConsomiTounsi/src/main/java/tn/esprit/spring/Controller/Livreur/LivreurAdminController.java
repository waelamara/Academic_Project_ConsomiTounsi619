package tn.esprit.spring.Controller.Livreur;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.Model.User;
import tn.esprit.spring.Repository.LivreurRepository;
import tn.esprit.spring.Repository.UserRepository;
import tn.esprit.spring.Service.GestionUser.UserService;
import tn.esprit.spring.Service.Livreur.LivreurService;

@Controller(value = "LivreurAdminController")
@ELBeanName(value = "LivreurAdminController")
@Join(path = "/LivreurAdmin", to = "/LivreurAdmin.jsf")
public class LivreurAdminController {
	@Autowired
	UserService UserService;
	@Autowired
	UserRepository UserRepository;
	
	//esma3ni hani bech njareb na3mel initialize lahna amma mana3refch ken tji mrgl or yo93edou user w kol mata3mel refresh yetzadou mara o5ra

	
	/*get all Livreur*/
	public List<User> getAllliv(){
		 List<Long> listeliv1=UserRepository.Listettlivreur();
	
			List<User> listeliv = new ArrayList();
		 User u = new User();
		 for(Long  a : listeliv1)
		 {
			 u=UserService.findOne(a);
			 if((u.getEtatD().equals("waiting")||(u.getEtatD().equals("accepted")))||(u.getEtatD().equals("refused")))
			 {
				 listeliv.add(u);
			 }
			 
			 //console win
		 }
		 //ntestiwha nrmlmnt haka temshi tra jareb
		 
		return  listeliv;
		
	}
	/*Accepter le livreur*/
	@Transactional
	public String accepterliv(long id){
		String navigateTo = "/LivreurAdmin.xhtml"; 
		UserRepository.ConfirmerLiv("accepted", id);
		return navigateTo;
		
		
	}
	/*suppr le livreur*/
	@Transactional
	public String supprimerliv(long id){
		
		String navigateTo = "/LivreurAdmin.xhtml";
		UserRepository.ConfirmerLiv("deleted",id);
		return navigateTo;
	}
	
	/*refuser le livreur*/
	@Transactional
	public String refuserliv(long id){
		
		String navigateTo = "/LivreurAdmin.xhtml";
		UserRepository.ConfirmerLiv("refused", id);
		return navigateTo;
	}
	

}

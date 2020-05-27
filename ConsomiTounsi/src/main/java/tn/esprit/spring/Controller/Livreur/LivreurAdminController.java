package tn.esprit.spring.Controller.Livreur;

import java.util.List;

import javax.transaction.Transactional;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.Model.Livreur;
import tn.esprit.spring.Repository.LivreurRepository;
import tn.esprit.spring.Service.Livreur.LivreurService;

@Controller(value = "LivreurAdminController")
@ELBeanName(value = "LivreurAdminController")
@Join(path = "/LivreurAdmin", to = "/LivreurAdmin.jsf")
public class LivreurAdminController {
	@Autowired
	LivreurService LivreurService;
	@Autowired
	LivreurRepository L;
	
	/*get all Livreur*/
	public List<Livreur> getAllliv(){
		
		return LivreurService.findall();
		
	}
	/*Accepter le livreur*/
	@Transactional
	public String accepterliv(long id){
		String navigateTo = "/LivreurAdmin.xhtml"; 
		L.ConfirmerLiv("accepted", id);
		return navigateTo;
		
		
	}
	/*suppr le livreur*/
	public String supprimerliv(long id){
		
		String navigateTo = "/LivreurAdmin.xhtml";
		L.deleteById(id);
		return navigateTo;
	}
	
	/*refuser le livreur*/
	@Transactional
	public String refuserliv(long id){
		
		String navigateTo = "/LivreurAdmin.xhtml";
		L.ConfirmerLiv("refused", id);
		return navigateTo;
	}
	

}

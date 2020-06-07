package tn.esprit.spring.Controller.Livreur;

import javax.transaction.Transactional;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.Repository.UserRepository;

@Controller(value = "LivreurAcceuilController")
@ELBeanName(value = "LivreurAcceuilController")
@Join(path = "/LivreurAcceuil", to = "/LivreurAcceuil.jsf")
public class LivreurAcceuilController {
	@Autowired
	UserRepository u;
	
	/*Free le dispo*/
	@Transactional
	public String freeDispo(long id){
		System.out.println("id livreur hetha"+id);
		String navigateTo = "/LivreurAcceuil.xhtml"; 
		u.ChangeDispo("free", id);
		return navigateTo;
	}
	/*busy le dispo*/
	@Transactional
	public String busyDispo(long id){
		System.out.println("id livreur hetha"+id);
		String navigateTo = "/LivreurAcceuil.xhtml"; 
		u.ChangeDispo("waiting", id);
		return navigateTo;
	}
}

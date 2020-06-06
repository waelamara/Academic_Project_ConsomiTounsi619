package tn.esprit.spring.Controller.Livreur;

import java.util.ArrayList;
import java.util.List;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.Model.User;
import tn.esprit.spring.Repository.UserRepository;
import tn.esprit.spring.Service.GestionUser.UserService;

@Controller(value = "LivreurDispoController")
@ELBeanName(value = "LivreurDispoController")
@Join(path = "/LivreurDispo", to = "/LivreurDispo.jsf")
public class LivreurDispoController {
	@Autowired
	UserRepository UserRepository;
	@Autowired
	UserService UserService;
	
	/*get all Livreur disponible*/
	public List<User> getAlllivdispo(){
		 List<Long> listeliv1=UserRepository.Listettlivreur();
			
			List<User> listeliv = new ArrayList();
		 User u = new User();
		 for(Long  a : listeliv1)
		 {
			 u=UserService.findOne(a);
			 if(u.getDisponible().equals("free")&&(u.getEtatD().equals("accepted")))
			 {
				 listeliv.add(u);
			 }
			
			 //console win
		 }
		 return listeliv;
		 //ntestiwha nrmlmnt haka temshi tra jareb
		 
		
	}

}

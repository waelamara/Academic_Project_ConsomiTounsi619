package tn.esprit.spring.Controller.Livreur;

import java.util.ArrayList;
import java.util.List;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.Model.Statliv;
import tn.esprit.spring.Model.User;
import tn.esprit.spring.Repository.UserRepository;
import tn.esprit.spring.Service.GestionUser.UserService;

@Controller(value = "StatistiqueLivController")
@ELBeanName(value = "StatistiqueLivController")
@Join(path = "/StatLiv", to = "/statistiqueliv.jsf")
public class StatistiqueLivController {
	@Autowired
	UserRepository u;
	@Autowired
	UserService UserService;
	
	protected List<Statliv> TOP10liv;

	public List<Statliv> getTOP10liv() {
		return TOP10liv;
	}

	public void setTOP10liv(List<Statliv> tOP10liv) {
		TOP10liv = tOP10liv;
	}
	
	public void StatTop10liv(){
		TOP10liv = new ArrayList<>();
		List<Long> Liste10Liv = new ArrayList<>();
		Liste10Liv=u.Top10nbLivreur();
		User DeliveryM = new User();
		for(Long i :Liste10Liv)
		{
			DeliveryM = UserService.findOne(i);
			TOP10liv.add(new Statliv(DeliveryM.getNbMission(),DeliveryM.getUsername()));
			
		}
		
		
	}
    

}

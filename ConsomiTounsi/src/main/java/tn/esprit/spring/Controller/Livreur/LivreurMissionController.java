package tn.esprit.spring.Controller.Livreur;

import java.util.List;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.Model.Livraison;
import tn.esprit.spring.Repository.LivraisonRepository;

@Controller(value = "LivreurMissionController")
@ELBeanName(value = "LivreurMissionController")
@Join(path = "/LivreurMission", to = "/Livreur_mission.jsf")
public class LivreurMissionController {
	@Autowired
	LivraisonRepository L;
	
	public List<Livraison> ListeMissions(long idD)
	{  System.out.println("atheya id l livreur"+idD);
		return L.Listedemission(idD);
	}

}

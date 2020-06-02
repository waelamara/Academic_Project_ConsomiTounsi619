package tn.esprit.spring.Controller.Livreur;

import java.util.List;

import javax.transaction.Transactional;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.Model.Livraison;
import tn.esprit.spring.Repository.LivraisonRepository;

@Controller(value = "LivreurMissionTodayController")
@ELBeanName(value = "LivreurMissionTodayController")
@Join(path = "/LivreurMissionToday", to = "/Livreur_missionToday.jsf")
public class LivreurMissionTodayController {
	@Autowired
	LivraisonRepository L;
	
	public List<Livraison> ListeMissions(long idD)
	{  System.out.println("atheya id l livreur"+idD);
		return L.ListedemissionToday(idD);
	}
	
	@Transactional
	public String actionDone(Integer idLiv)
	{
		String Navigate="/Livreur_mission.xhtml";
		L.DoneMission(1,idLiv);
		return Navigate;
		
	}

}

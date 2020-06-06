package tn.esprit.spring.Controller.Livreur;

import java.util.List;

import javax.transaction.Transactional;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.Model.Livraison;
import tn.esprit.spring.Repository.LivraisonRepository;

@Controller(value = "LivreurMissionMonthController")
@ELBeanName(value = "LivreurMissionMonthController")
@Join(path = "/LivreurMissionMonth", to = "/Livreur_missionMonth.jsf")
public class LivreurMissionMonthController {
	@Autowired
	LivraisonRepository L;
	
	public List<Livraison> ListeMissions(long idD)
	{  
		return L.ListedemissionMonth(idD);
	}
	
	@Transactional
	public String actionDone(Integer idLiv)
	{
		String Navigate="/Livreur_mission.xhtml";
		L.DoneMission(1,idLiv);
		return Navigate;
		
	}


}

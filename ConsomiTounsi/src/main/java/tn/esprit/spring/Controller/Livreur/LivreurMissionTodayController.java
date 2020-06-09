package tn.esprit.spring.Controller.Livreur;

import java.util.List;

import javax.transaction.Transactional;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.Model.Livraison;
import tn.esprit.spring.Model.User;
import tn.esprit.spring.Repository.LivraisonRepository;
import tn.esprit.spring.Repository.UserRepository;
import tn.esprit.spring.Service.GestionUser.UserService;
import tn.esprit.spring.Service.Panier.CommandeImpl;

@Controller(value = "LivreurMissionTodayController")
@ELBeanName(value = "LivreurMissionTodayController")
@Join(path = "/LivreurMissionToday", to = "/Livreur_missionToday.jsf")
public class LivreurMissionTodayController {
	@Autowired
	LivraisonRepository L;
	@Autowired
	UserRepository us;
	@Autowired
	UserService uss;
	@Autowired
	CommandeImpl commandeImpl;
	
	public List<Livraison> ListeMissions(long idD)
	{  System.out.println("atheya id l livreur"+idD);
		return L.ListedemissionToday(idD);
	}
	
	@Transactional
	public String actionDone(long idLiv,long idDelivery)
	{
		String Navigate="/Livreur_missionToday.xhtml";
		User u = new User();
		System.out.println(idDelivery);
		u=us.getOne(idDelivery);
			Livraison l=L.getOne(idLiv);
		commandeImpl.payerApresLivraison(l.getCommande().getId());
		Integer nbmission =u.getNbMission();
		nbmission=nbmission+1;
		u.setNbMission(nbmission);
		uss.save(u);
		L.DoneMission(1,idLiv);
		return Navigate;
		
	}

}

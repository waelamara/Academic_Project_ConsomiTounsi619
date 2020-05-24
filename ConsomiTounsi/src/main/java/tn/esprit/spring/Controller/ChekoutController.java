package tn.esprit.spring.Controller;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.Service.Livraison.LivraisonService;

@Controller(value = "chekoutController")
@ELBeanName(value = "chekoutController")
@Join(path = "/Chekout", to = "/checkout2.jsf")
public class ChekoutController {

	@Autowired
	LivraisonService LivraisonService;
	private String adresse;

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public void affecterfraisLiv(String adress, long idc) {

		float prixfinal = LivraisonService.CalculerFraisLivraison(adresse, idc);

		System.out.println("aaaaaaaaaaaa   :" + prixfinal);
		LivraisonService.AjouterLivraison(idc, adresse);
	}

}

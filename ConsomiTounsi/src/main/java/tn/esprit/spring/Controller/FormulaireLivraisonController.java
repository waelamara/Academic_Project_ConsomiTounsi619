package tn.esprit.spring.Controller;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.Service.Livraison.LivraisonService;

@Scope(value = "session")
@Controller(value = "FormulaireLivraisonController")
@ELBeanName(value = "FormulaireLivraisonController")
@Join(path = "/Map/{id}", to = "/LivraisonFormulaire.jsf")
public class FormulaireLivraisonController {
	@Autowired
	LivraisonService LivraisonService;
	
	public String Lieux;

	public String getLieux() {
		return Lieux;
	}

	public void setLieux(String lieux) {
		Lieux = lieux;
	}
	
	public String pp;
	
	
	
	public String getPp() {
		return pp;
	}

	public void setPp(String pp) {
		this.pp = pp;
	}

	public void addMap(long id,String pp) {
		int a =3;
		LivraisonService.AjouterLivraison(id,pp,a);
	}
	
	

}

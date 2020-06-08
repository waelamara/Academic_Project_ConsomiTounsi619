package tn.esprit.spring.Controller.Forum;

import javax.mail.MessagingException;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.Service.Forum.ISujetService;

@Controller(value = "ControllerGift")
@ELBeanName(value = "ControllerGift")
@Join(path = "/gift", to = "/fourm/gift.jsf")
public class GiftController {
	@Autowired
	ISujetService iSujetService;
	private int nbpoint;

	public int getNbpoint() {
		return nbpoint;
	}

	public void setNbpoint(int nbpoint) {
		this.nbpoint = nbpoint;
	}
public void produit_gangnant(){
	try {
		iSujetService.produit_gangnant(nbpoint);
	} catch (MessagingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}

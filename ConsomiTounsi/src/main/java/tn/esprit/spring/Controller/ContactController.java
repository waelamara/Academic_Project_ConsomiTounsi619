package tn.esprit.spring.Controller;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.Model.reclamation;
import tn.esprit.spring.Service.Panier.CommandeImpl;
import tn.esprit.spring.Service.Reclamation.ReclamationService;

@Scope(value = "session")
@Controller(value = "ContactController")
@ELBeanName(value = "ContactController")
@Join(path = "/Contact", to = "/Contact.jsf")
public class ContactController {
	@Autowired
	ReclamationService ReclamationService;
	@Autowired
	CommandeImpl CommandeDAO;
	
	public String titre;
	public String description;
	private reclamation rec;
	
	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public  void  addreclamation() {
		
		reclamation rec= new reclamation (titre,description);
		System.out.println(titre);
		System.out.println(description);
		ReclamationService.save1(rec);
		
	}

	public reclamation getRec() {
		return rec;
	}

	public void setRec(reclamation rec) {
		this.rec = rec;
	}
		
	}
	
	



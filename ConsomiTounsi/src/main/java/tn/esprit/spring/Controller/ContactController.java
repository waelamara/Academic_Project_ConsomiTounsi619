package tn.esprit.spring.Controller;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.Controller.GestionUser.LoginController;
import tn.esprit.spring.Model.Commande;
import tn.esprit.spring.Model.User;
import tn.esprit.spring.Model.reclamation;
import tn.esprit.spring.Repository.UserRepository;
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
	@Autowired
	LoginController loginController;
	@Autowired 
	UserRepository UserRepository;
	
	public String titre;
	public String description;
	private reclamation rec;
	public Long idcommande;
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
	
	public Long getIdcommande() {
		return idcommande;
	}

	public void setIdcommande(Long idcommande) {
		this.idcommande = idcommande;
	}

	@Transactional
	public  String  addreclamation(long iduser) {
		
		String navigateTo = "/acceuil.xhtml"; 
		reclamation rec= new reclamation (titre,description);
        rec.setDateRec(LocalDate.now());
        User u =UserRepository.getOne(iduser);
        Commande C = new Commande();
        C=CommandeDAO.findOne(idcommande);
        rec.setCommande(C);
        rec.setUser(u);
        System.out.println(u);
		ReclamationService.save1(rec);
		return navigateTo;
		
	}

	public reclamation getRec() {
		return rec;
	}

	public void setRec(reclamation rec) {
		this.rec = rec;
	}
	
	public List<Long> findIdCommande(Long iduser1){
		return ReclamationService.ListeCommandeparuser(iduser1);
	}
		
	}
	
	



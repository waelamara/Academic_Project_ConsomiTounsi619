package tn.esprit.spring.Controller.Charite;

import java.util.List;

import javax.persistence.ManyToOne;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.annotation.JsonIgnore;

import tn.esprit.spring.Model.Charite.Charite;
import tn.esprit.spring.Model.Charite.Endroit;
import tn.esprit.spring.Model.Charite.Events;
import tn.esprit.spring.Repository.Charite.EndroitRepository;
import tn.esprit.spring.Service.Charite.ChariteDAO;
import tn.esprit.spring.Service.Charite.EndroitDAO;
import tn.esprit.spring.Service.Charite.EventsDAO;
import tn.esprit.spring.Service.Panier.CommandeImpl;


@Controller(value = "ControllerEndroit")
@ELBeanName(value = "ControllerEndroit")
@Join(path = "/endroit", to = "/Endroit.jsf")
public class ControllerEndroit {
	@Autowired
	EndroitDAO endroitDAO;
	@Autowired
	EventsDAO eventDAO;
	@Autowired
	CommandeImpl commandeDao;
	@Autowired
	ChariteDAO chariteDAO;
	@Autowired
	private EndroitRepository endroitRepository;
	
	private Long id;
	private String statu;
	private String emplacement;
	private int nbplace;

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStatu() {
		return statu;
	}
	public void setStatu(String statu) {
		this.statu = statu;
	}
	public String getEmplacement() {
		return emplacement;
	}
	public void setEmplacement(String emplacement) {
		this.emplacement = emplacement;
	}
	public int getNbplace() {
		return nbplace;
	}
	public void setNbplace(int nbplace) {
		this.nbplace = nbplace;
	}
	@Transactional
	public String addEndroit() {
		Endroit e = new Endroit();
		e.setEmplacement(emplacement);
		e.setNbplace(nbplace);
		e.setStatu("disponible");
		endroitDAO.saveEndroit1(e);

		return "/Endroit.xhtml?faces-redirect=true";
	}
	public List<Endroit> getAllEndroit() {
		
		return endroitDAO.getAllEndroitList();
	}
public List<Endroit> getAllEndroitD() {
		
		return endroitDAO.getAllEndroitDi();
	}
public List<Endroit> getAllEndroitR() {
	
	return endroitDAO.getAllEndroitR();
}
	public static Long idev ;
	public static Long iden ;

	public String reserveE(Long ideventss) {
		
		 idev = ideventss;
		///endroitDAO.saveEndroit(ideventss, new Endroit());
		System.out.println(idev);
		return "/Endroit.xhtml?faces-redirect=true";
	}
	@Transactional
	public String reserveEndroit(Long idendroit) {
		iden = idendroit;
		Endroit e2 = endroitDAO.findOne(idendroit);
		Events d1 = eventDAO.findOne(idev);
		int nb = e2.getNbplace();
		System.out.println("d1 =" +d1.getId());
		System.out.println("e2 =" +e2.getId());
		System.out.println("e2 =" +nb);
		String message = "this place is reserved ";
		String message1 = "reserve";
		String message2 = "number of places less than number of places in its event";
		int nbPEndroit = e2.getNbplace();
		int nbPEvent = d1.getNbplace();
		if ((e2.getStatu().equals("disponible")) && (nbPEndroit > nbPEvent)) {
			e2.setNbplace(e2.getNbplace());
			e2.setEmplacement(e2.getEmplacement());
			e2.setEventss(e2.getEventss());
			e2.setStatu("reserve");
			endroitDAO.saveEndroit(d1.getId(), e2);
			return "/EventAdmin.xhtml?faces-redirect=true";
		} else if (nbPEndroit < nbPEvent) {
			return message2;
		} else {
			return message ;
		}
		
		

	}
	@Transactional
	public String dreserveEndroit(Long idendroit) {
		iden = idendroit;
		Endroit e2 = endroitDAO.findOne(idendroit);
		
		String message = "this place is reserved ";
		if ((e2.getStatu().equals("reserve"))) {
			e2.setNbplace(e2.getNbplace());
			e2.setEmplacement(e2.getEmplacement());
			e2.setEventss(null);
			e2.setStatu("disponible");
			endroitDAO.saveEndroit2(idendroit, e2);
			return "/Endroit.xhtml?faces-redirect=true";
		} else {
			return message ;
		}
		
		

	}
	
	
}

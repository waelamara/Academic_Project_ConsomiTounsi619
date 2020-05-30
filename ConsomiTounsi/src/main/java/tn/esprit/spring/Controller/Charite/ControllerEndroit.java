package tn.esprit.spring.Controller.Charite;

import java.util.List;

import javax.persistence.ManyToOne;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.annotation.JsonIgnore;

import tn.esprit.spring.Model.Charite.Charite;
import tn.esprit.spring.Model.Charite.Endroit;
import tn.esprit.spring.Model.Charite.Events;
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
	
	private Long Id;
	private String statu;
	private String emplacement;
	private int nbplace;
	private Events eventss;
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
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
	public Events getEventss() {
		return eventss;
	}
	public void setEventss(Events eventss) {
		this.eventss = eventss;
	}
	
	public List<Endroit> getAllEndroit() {
		
		return endroitDAO.getAllEndroitList();
	}
	public static Long idev ;
	public static Long iden ;

	public String reserveE(Long ideventss) {
		
		 idev = ideventss;
		///endroitDAO.saveEndroit(ideventss, new Endroit());
		System.out.println(idev);
		return "/Endroit.xhtml?faces-redirect=true";
	}
	public String reserveEndroit(Long idendroit) {
		iden = idendroit;
		Endroit e2 = endroitDAO.findOne(iden);
		Events d1 = eventDAO.findOne(idev);
		System.out.println("d1 =" +d1.getId());
		System.out.println("e2 =" +e2.getId());
		String message = "this place is reserved ";
		String message1 = "Successful";
		String message2 = "number of places less than number of places in its event";

		/*int nbPEndroit = e2.getNbplace();
		int nbPEvent = d1.getNbplace();*/
	//System.out.println("nb en =" +d1.getNbplace());
	//	System.out.println("nb ev =" +e2.getNbplace());
		return "/EventAdmin.xhtml?faces-redirect=true";
		/*if ((e2.getStatu().equals("disponible")) && (nbPEndroit > nbPEvent)) {
			e2.setNbplace(e2.getNbplace());
			e2.setEmplacement(e2.getEmplacement());
			e2.setEventss(e2.getEventss());
			e2.setStatu("Reservé");
			endroitDAO.saveEndroit(d1.getId(), e2);
			return "/EventAdmin.xhtml?faces-redirect=true";
		} else if (nbPEndroit < nbPEvent) {
			return message2;
		} else {
			return message ;
		}*/
		
		

	}

}

package tn.esprit.spring.Controller.Charite;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.Model.Charite.Events;
import tn.esprit.spring.Service.Charite.EventsDAO;

@Controller(value = "ControllerAfficheEvents")
@ELBeanName(value = "ControllerAfficheEvents")
@Join(path = "/afficheEvent", to = "/EventAdmin.jsf")
@ViewScoped
public class ControllerAfficheEvents {
	@Autowired
	EventsDAO eventDAO;
	private String titre;
	
	 private RepeatPaginator2 paginatorRec;
	 
	 public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public List<Events> getAllEvents() {
			return eventDAO.getAllEventsList();
		}

	public RepeatPaginator2 getPaginatorRec() {
		return paginatorRec;
	}

	public void setPaginatorRec(RepeatPaginator2 paginatorRec) {
		this.paginatorRec = paginatorRec;
	}
	@PostConstruct
	public void init(){
		List<Events> c= getAllEvents();
	paginatorRec = new RepeatPaginator2(c);
}
	
	public List<Events> findLikeNameM(String title) {
		return eventDAO.findLikeName(titre);
	}
}

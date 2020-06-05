package tn.esprit.spring.Controller.Charite;

import java.util.List;

import javax.annotation.PostConstruct;

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
public class ControllerAfficheEvents {
	
}

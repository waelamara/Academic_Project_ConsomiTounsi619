package tn.esprit.spring.Controller.Forum;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.Model.Chart.SexeC;
import tn.esprit.spring.Service.Forum.ISujetService;
import tn.esprit.spring.Service.GestionUser.ChartService;
import tn.esprit.spring.Service.GestionUser.UserService;

@Controller(value = "ControllerGift")
@ELBeanName(value = "ControllerGift")
@Join(path = "/gift", to = "/fourm/gift.jsf")
public class GiftController {
	@Autowired
	ISujetService iSujetService;
	private int nbpoint;
	@Autowired
	ChartService chartService;
	@Autowired
	UserService userService;
	protected List<SexeC> listnbpoints ;
	
	public int getNbpoint() {
		return nbpoint;
	}

	public void setNbpoint(int nbpoint) {
		this.nbpoint = nbpoint;
	}
	
public List<SexeC> getListnbpoints() {
		return listnbpoints;
	}

	public void setListnbpoints(List<SexeC> listnbpoints) {
		this.listnbpoints = listnbpoints;
	}

public void produit_gangnant(){
	try {
		iSujetService.produit_gangnant(nbpoint);
	} catch (MessagingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public int nmbureOfuser(){
	return userService.nbuser();
}
public float moyenneNombrefidelet(){
	return userService.moyennenbpointfiedelete();
}
public void getUserSelonSexe()
{
	listnbpoints = new ArrayList<SexeC>();
	
	listnbpoints.add(new SexeC("Loyalty point <100",userService.getmbreUsersbyPointfideletInf100()));
	listnbpoints.add(new SexeC("100<=Loyalty point<300",userService.getmbreUsersbyPointfideletBetwen100300()));
	listnbpoints.add(new SexeC("Loyalty point>300",userService.getmbreUsersbyPointfideletSup()));

	
}
}

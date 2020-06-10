package tn.esprit.spring.Controller;

import java.util.Map;

import javax.faces.context.FacesContext;
import javax.transaction.Transactional;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.Model.reclamation;
import tn.esprit.spring.Repository.reclamationRepository;

@Controller(value = "ReclamationAdminRController")
@ELBeanName(value = "ReclamationAdminRController")
@Join(path = "/RepondreRec", to = "/Reclamationrepondre.jsf")
public class ReclamationAdminRController {

	@Autowired
	reclamationRepository ru;
	String a;
	public String reponse;
	// String b;
     
	private String getCountryFromJSF(FacesContext context) {
		Map<String, String> parameters = context.getExternalContext().getRequestParameterMap();
		return parameters.get("idrec");
	}

	public String getReponse() {
		return reponse;
	}

	public void setReponse(String reponse) {
		this.reponse = reponse;
	}

	public long outcome() {
		FacesContext context = FacesContext.getCurrentInstance();
		a = getCountryFromJSF(context);
		return Long.parseLong(a);

	}

	@Transactional
	public reclamation retourReclamation(long idrec1) {
		idrec1 = outcome();
		reclamation r = new reclamation();
		r = ru.findById(idrec1).get();
		return r;

	}
	@Transactional
	public String reponseReclamation(long idreeec){
		String navigateTo = "ReclamationAdmin.xhtml";
    	ru.RepondreReclamation(reponse, idreeec);
		System.out.println("aaaaaaaaaaaaaaaaa"+idreeec);
		return navigateTo;
	}

}

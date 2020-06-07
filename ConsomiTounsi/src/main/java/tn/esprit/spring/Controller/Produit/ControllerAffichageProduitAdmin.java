package tn.esprit.spring.Controller.Produit;

import java.util.Map;

import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.stereotype.Controller;

@Controller(value = "ControllerAffichageProduitAdmin")
@ELBeanName(value = "ControllerAffichageProduitAdmin")
@Join(path = "/afficherProduit", to = "/pages/AffichageProduitAdmin.jsf")
public class ControllerAffichageProduitAdmin {
	
	String a;

	private String getCountryFromJSF(FacesContext context) {
		Map<String, String> parameters = context.getExternalContext().getRequestParameterMap();
		return parameters.get("idprod");
	}

	public Long outcome() {
		FacesContext context = FacesContext.getCurrentInstance();
		a = getCountryFromJSF(context);
		System.out.println("****************"+a);
		return Long.parseLong(a);

	}
 
	
}

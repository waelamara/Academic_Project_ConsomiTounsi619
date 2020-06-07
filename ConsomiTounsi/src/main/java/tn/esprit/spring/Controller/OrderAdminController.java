package tn.esprit.spring.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import tn.esprit.spring.Controller.Forum.RepeatPaginator;
import tn.esprit.spring.Model.Commande;
import tn.esprit.spring.Model.lignecommandeproduit;
import tn.esprit.spring.Model.Forum.Sujet;
import tn.esprit.spring.Service.Panier.CommandeImpl;
import tn.esprit.spring.Service.Panier.FactureImpl;
import tn.esprit.spring.Service.Panier.LigneCommandeImpl;


@Controller(value = "OrderAdminController")
@ELBeanName(value = "OrderAdminController")
@Join(path = "/OrderAdmin", to = "/Order.jsf")
public class OrderAdminController {

	@Autowired
	CommandeImpl CommandeDao;
	
	@Autowired
	FactureImpl factureDAO;
	

	@Autowired
	ServletContext context;
	
	@Autowired
	LigneCommandeImpl ligneCommandeDao;
	
	private String type;
	
	
	
	
	private RepeatPaginator paginator;
//	@PostConstruct
//	public void init(){
//		List<Commande> c= getAllCommande();
//	paginator = new RepeatPaginator(c);
//}
	
//    public RepeatPaginator getPaginator() {
//   	 
//        return paginator;
//    }
	
	


public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


public List<Commande> getAllCommande() {
		return CommandeDao.findAll();
	}	


public void facturepdf (long id_facture)
{
	factureDAO.facturepdf(id_facture);
}


public List<Commande> CommandeparType(String types) {
	System.out.print(type);
	return CommandeDao.CommandeparType(type);
}

public List<lignecommandeproduit> panierParIdCommande(long idCommande)
{
	 return ligneCommandeDao.panierParIdCommande(outcome());
}
String a;
private String getCountryFromJSF(FacesContext context) {
    Map<String, String> parameters = context.getExternalContext().getRequestParameterMap();
    return parameters.get("idCommande");
}
 public Long outcome() {
        FacesContext context = FacesContext.getCurrentInstance();
        a = getCountryFromJSF(context);
        System.out.println(a);
        return Long.parseLong(a);
        
    }





}

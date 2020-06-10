
package tn.esprit.spring.Controller.Stock;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.annotation.JsonFormat;

import tn.esprit.spring.Model.Produit.Produit;
import tn.esprit.spring.Model.Stock.Stock;
import tn.esprit.spring.Repository.Produit.ProduitRepository;
import tn.esprit.spring.Service.Produit.IProduitService;
import tn.esprit.spring.Service.Stock.IStockService;




@Controller(value = "JsfStockController")
@ELBeanName(value = "JsfStockController")
@Join(path = "/stock", to = "/stock.jsf")
public class JsfStockController {
	
	@Autowired
	IStockService stockservice;
	@Autowired
	IProduitService iproduitService;
	private Long idstock;
	private String nom_stock;
	private int quantite;
	@Temporal (TemporalType.DATE)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date validite;
	private float prixdevente;
	private Long stockIdToBeUpdated;
	



	
	
	public Long getStockIdToBeUpdated() {
		return stockIdToBeUpdated;
	}
	public void setStockIdToBeUpdated(Long stockIdToBeUpdated) {
		this.stockIdToBeUpdated = stockIdToBeUpdated;
	}
	public Long getIdstock() {
		return idstock;
	}
	public void setIdstock(Long idstock) {
		this.idstock = idstock;
	}
	public String getNom_stock() {
		return nom_stock;
	}
	public void setNom_stock(String nom_stock) {
		this.nom_stock = nom_stock;
	}
public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	public Date getValidite() {
		return validite;
	}
	public void setValidite(Date validite) {
		this.validite = validite;
	}
	public float getPrixdevente() {
		return prixdevente;
	}
	public void setPrixdevente(float prixdevente) {
		this.prixdevente = prixdevente;
	}


	
	



	public JsfStockController() {
		super();
	}
	
	public JsfStockController(String nom_stock, int quantite, Date validite, float prixdevente) {
		super();
		
		this.nom_stock = nom_stock;
		this.quantite = quantite;
		this.validite = validite;
		this.prixdevente = prixdevente;
	}

	public List<Stock> allStock() {
		return stockservice.allStock();
	}
	 
	public String gopage(Long idprod){
		
		return "/addstock.xhtml?faces-redirect=true&idprod=" + idprod.toString();
	}
	
public String gopagestock(Long idstock){
	
		return "/updatestock.xhtml?faces-redirect=true&idstock=" + idstock.toString();
	}
	
	
	
	public String addstock(long idprod) {
		String navigateTo = "/stock";
		 stockservice.ajouterStockbyProd(new Stock(nom_stock,quantite,validite,prixdevente) ,idprod);
		return navigateTo;

		}
	
	
public List<Produit> getProduits() {
		
		return stockservice.getProduits();
	}


public void deletestock(Long idstock) {

	stockservice.deleteStockById(idstock);

}


public void displayStock(Stock stock){
	this.setNom_stock(stock.getNom_stock());
	this.setQuantite(stock.getQuantite());
	this.setValidite(stock.getValidite());
	this.setPrixdevente(stock.getPrixdevente());
	this.setStockIdToBeUpdated(stock.getIdstock());
	
}

public String updateStockjsf(Long idstock){
	String navigateTo = "/stock";
	Stock s=stockservice.getStockbyId(idstock);
	
	s.setNom_stock(nom_stock);
	System.out.println("****************"+nom_stock);
	s.setPrixdevente(prixdevente);
	System.out.println("****************"+prixdevente);
	s.setQuantite(quantite);
	s.setValidite(validite);
	stockservice.saveStock(s);
	return navigateTo;
}

String a;

private String getCountryFromJSF(FacesContext context) {
	Map<String, String> parameters = context.getExternalContext().getRequestParameterMap();
	return parameters.get("idstock");
}

public Long outcome() {
	FacesContext context = FacesContext.getCurrentInstance();
	a = getCountryFromJSF(context);
	System.out.println("****************"+a);
	return Long.parseLong(a);

}

}

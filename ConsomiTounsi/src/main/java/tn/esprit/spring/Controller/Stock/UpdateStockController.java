package tn.esprit.spring.Controller.Stock;

import java.util.Date;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.transaction.Transactional;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.annotation.JsonFormat;

import tn.esprit.spring.Model.Stock.Stock;
import tn.esprit.spring.Service.Stock.IStockService;

@Controller(value = "UpdateStockController")
@ELBeanName(value = "UpdateStockController")
@Join(path = "/updatestock", to = "/updatestock.jsf")
public class UpdateStockController {
	@Autowired
	IStockService stockservice;
	private Long idstock;
	private String nom_stock;
	private int quantite;
	@Temporal (TemporalType.DATE)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date validite;
	private float prixdevente;
	
	
	
	
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
	
	public Stock getStockbyId(Long idstock) {
		return  stockservice.getStockbyId(idstock);
	}
	
	public String updateStockjsf(Long idstock){
		String navigateTo = "/stock";
		Stock s=stockservice.getStockbyId(idstock);
		System.out.println("*****************"+s.getNom_stock());
		s.setNom_stock(nom_stock);
		System.out.println("****************"+getNom_stock());
		s.setPrixdevente(prixdevente);
		System.out.println("****************"+prixdevente);
		s.setQuantite(quantite);
		s.setValidite(validite);
		stockservice.addorupdate(s);
		return navigateTo;
	}


	
}

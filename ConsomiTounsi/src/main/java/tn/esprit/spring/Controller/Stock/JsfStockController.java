package tn.esprit.spring.Controller.Stock;

import java.util.Date;
import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.annotation.JsonFormat;

import tn.esprit.spring.Model.Stock.Stock;
import tn.esprit.spring.Service.Stock.IStockService;




@Controller(value = "JsfStockController")
@ELBeanName(value = "JsfStockController")
@Join(path = "/stock", to = "/stock.jsf")
public class JsfStockController {
	
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


	
	



	public JsfStockController() {
		super();
	}
	
	public JsfStockController(Long idstock, String nom_stock, int quantite, Date validite, float prixdevente) {
		super();
		this.idstock = idstock;
		this.nom_stock = nom_stock;
		this.quantite = quantite;
		this.validite = validite;
		this.prixdevente = prixdevente;
	}
	public List<Stock> allStock() {
		return stockservice.allStock();
	}
	
	
	
	
	public Stock addstock(@RequestBody Stock stock) {
		return stockservice.saveStock(stock);

		}

}

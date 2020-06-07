package tn.esprit.spring.Controller.Stock;

import java.util.Date;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	private Long stockIdToBeUpdated;
	
	
	public UpdateStockController() {
		super();
		
		
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


	
}

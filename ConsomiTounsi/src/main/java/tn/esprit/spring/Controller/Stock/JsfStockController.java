package tn.esprit.spring.Controller.Stock;

import java.util.List;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.Model.Stock.Stock;
import tn.esprit.spring.Service.Stock.IStockService;




@Controller(value = "JsfStockController")
@ELBeanName(value = "JsfStockController")
@Join(path = "/stock", to = "/stock.jsf")
public class JsfStockController {
	
	@Autowired
	IStockService stockservice;
	
	
	public List<Stock> allStock() {
		return stockservice.allStock();
	}

}

<<<<<<< HEAD
package tn.esprit.spring.Controller.Stock;

import java.util.ArrayList;
import java.util.List;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.Model.Stock.StockByProd;
import tn.esprit.spring.Service.Stock.IStockService;

@Controller(value = "StatProdStockController")
@ELBeanName(value = "StatProdStockController")
@Join(path = "/statprodstock", to = "/statprodstock.jsf")
public class StatProdStockController {

	@Autowired
	IStockService stockservice;
	
	public List<StockByProd> stockbyprod;

	public List<StockByProd> getStockbyprod() {
		return stockbyprod;
	}

	public void setStockbyprod(List<StockByProd> stockbyprod) {
		this.stockbyprod = stockbyprod;
	}
	
	
	public void StatStock(){
		List<StockByProd> s=stockservice.quantiteByProd();
		stockbyprod=new ArrayList<>();
		for(StockByProd s1:s ){
			stockbyprod.add(new StockByProd(s1.getN(),s1.getNomproduit()));
		}
		
		
	}
}
=======
package tn.esprit.spring.Controller.Stock;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.stereotype.Controller;

@Controller(value = "StatProdStockController")
@ELBeanName(value = "StatProdStockController")
@Join(path = "/statprodstock", to = "/statprodstock.jsf")
public class StatProdStockController {

}
>>>>>>> branch 'master' of https://github.com/waelamara/ConsomiTounsi619

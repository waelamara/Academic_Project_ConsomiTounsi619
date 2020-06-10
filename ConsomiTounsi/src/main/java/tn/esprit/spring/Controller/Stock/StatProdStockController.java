package tn.esprit.spring.Controller.Stock;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.stereotype.Controller;

@Controller(value = "StatProdStockController")
@ELBeanName(value = "StatProdStockController")
@Join(path = "/statprodstock", to = "/statprodstock.jsf")
public class StatProdStockController {

}

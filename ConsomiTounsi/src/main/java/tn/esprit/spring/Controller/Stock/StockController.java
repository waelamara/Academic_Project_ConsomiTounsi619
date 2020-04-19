package tn.esprit.spring.Controller.Stock;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.Model.Rayon.Rayon;
import tn.esprit.spring.Model.Stock.Stock;
import tn.esprit.spring.Service.Stock.IStockService;

@RestController
@RequestMapping("/stock")
public class StockController {
	
	@Autowired
	IStockService stockservice;
	
///////////////////////////////////////////////////////////////////////////////
@PostMapping("/addstock")
@ResponseBody
public Stock addstock(@RequestBody Stock stock) {
return stockservice.saveStock(stock);

}
///////////////////////////////////////////////////////////////////////////

@PutMapping("/editstock")
@ResponseBody
public Stock editstock(@RequestBody Stock stock) {
return stockservice.updateStock(stock);
}

////////////////////////////////////////////////////////////////////////////////

@DeleteMapping("/deletestock/{idstock}")
public void deletestock(@PathVariable(name = "idstock") Long idstock) {

	stockservice.deleteStockById(idstock);

}
///////////////////////////////////////////////////////////////////////////

@RequestMapping(value = "/allstock")
public List<Stock> allStock() {
	return stockservice.allStock();
}

////////////////////////////////////////////////////////////////////////
@PutMapping("/affecterStockAProduit/{ids}/{idp}")
public void affecterStockAProduit(@PathVariable(value = "ids") Long idstock,@PathVariable(value = "idp") Long Idproduit){
	
	stockservice.affecterStockAProduit(idstock, Idproduit);
}
///////////////////////////////////////////
@PutMapping("/PrixVenteParStock/{idp}")
public void PrixVenteParStock(@PathVariable(value = "idp") Long idproduit){
	
	stockservice.PrixVenteParStock(idproduit);
	
}


/////////////////////////////////////////////////////////////////////////////
}

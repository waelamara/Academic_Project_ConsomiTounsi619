package tn.esprit.spring.Controller;

import java.util.ArrayList;
import java.util.List;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.Model.CategorieStat;
import tn.esprit.spring.Model.Statliv;
import tn.esprit.spring.Model.User;
import tn.esprit.spring.Model.Chart.SexeC;
import tn.esprit.spring.Service.Panier.LigneCommandeImpl;

@Controller(value = "OrderStatistiqueController")
@ELBeanName(value = "OrderStatistiqueController")
@Join(path = "/OrderStatistique", to = "/AdminStatistique.jsf")
public class OrderStatistiqueController {
	
	
	@Autowired
	LigneCommandeImpl ligneCommandeDao;
	
	protected List<CategorieStat> cs;
	
	protected List<CategorieStat> ps;
	
	
	
	
	public List<CategorieStat> getPs() {
		return ps;
	}
	public void setPs(List<CategorieStat> ps) {
		this.ps = ps;
	}
	public List<CategorieStat> getCs() {
		return cs;
	}
	public void setCs(List<CategorieStat> cs) {
		this.cs = cs;
	}
	
	public List<CategorieStat> NumCategorie2()
	{
		
		return ligneCommandeDao.NumCategorie2();
	}
	
	public void cat(){
		List<CategorieStat> c = ligneCommandeDao.NumCategorie2();
		cs= new ArrayList<CategorieStat>();
		for(CategorieStat c1 : c)
		{
			cs.add(new CategorieStat(c1.getAmount(),c1.getName()));
		}
		

}
	public void produit(){
		List<CategorieStat> p = ligneCommandeDao.NumProduitVendu2();
		ps= new ArrayList<CategorieStat>();
		for(CategorieStat p1 : p)
		{
			cs.add(new CategorieStat(p1.getAmount(),p1.getName()));
		}
		

}
	
}

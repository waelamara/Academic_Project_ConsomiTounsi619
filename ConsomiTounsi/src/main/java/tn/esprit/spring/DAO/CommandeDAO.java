package tn.esprit.spring.DAO;



import java.beans.Transient;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.Model.Commande;
import tn.esprit.spring.Model.LigneCommande;
import tn.esprit.spring.Repository.CommandeRepository;
@Service
public class CommandeDAO {
	@Autowired
	CommandeRepository commandeRepository;
	public Commande save (Commande c)
	{
		c.setDate(LocalDate.now());
		return commandeRepository.save(c);
	}
	//@Transient
    /*public Double getTotalOrderPrice() {
        double sum = 0D;
        List<LigneCommande> LigneCommandes = new ArrayList<LigneCommande>() ;//getOrderProducts()
        for (LigneCommande l : LigneCommandes) {
            sum += l.getPrice();
        }
        return sum;
    }*/
 

}

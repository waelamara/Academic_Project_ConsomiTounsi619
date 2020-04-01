package tn.esprit.spring.Service.Panier;



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
	public List<Commande> CommandeparType(String type) {
		return commandeRepository.CommandeparType(type);

	}
	public List<Commande> CommandeparClient(int id) {
		return commandeRepository.CommandeparClient(id);

	}
	public List<Commande> findAll() {
		return commandeRepository.findAll();
	}
	public void Delete(Commande c) {
		commandeRepository.delete(c);
	}

	public Commande findOne(Long id) {
		return commandeRepository.getOne(id);
	}
	public List< Commande> Commandeparcode( long code) {
		return commandeRepository.Commandeparcode(code);

	}
	public void PayerEnLigne(int idCommande)
	{
		commandeRepository.PayerEnLigne(idCommande);
	}
	public void PayerPorteaPorte(int idCommande)
	{
		commandeRepository.PayerPorteaPorte(idCommande);
	}
 

}

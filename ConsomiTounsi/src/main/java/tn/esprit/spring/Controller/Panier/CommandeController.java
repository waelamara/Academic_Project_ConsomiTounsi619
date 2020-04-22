package tn.esprit.spring.Controller.Panier;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import tn.esprit.spring.Model.Commande;
import tn.esprit.spring.Service.Panier.CommandeImpl;
@Controller
public class CommandeController {
	
	@Autowired
	CommandeImpl commandeDao;
	
	
	public Commande AjouterCommande(Commande c)
	{
		return commandeDao.save(c);
	}
	
	
	public List<Commande> getAllCommande() {
		return commandeDao.findAll();
	}
	
	
	public void DeleteCommande(Long idCommande) {
		Commande c =commandeDao.findOne(idCommande);
		commandeDao.Delete(c);
	}
	
	
	public List<Commande>Commandeparcode(long code) {
		
		return commandeDao.Commandeparcode(code);
	}
	
	
	public List<Commande> CommandeparType(String type) {
		return commandeDao.CommandeparType(type);
	}
	
	
	public List<Commande> CommandeparClient(int idUser) {
		return commandeDao.CommandeparClient(idUser);
	}
	
	
	public void PayerEnLigne(int idCommande,int idUser,String code)
	{
		commandeDao.PayerEnLigne(idCommande,idUser, code);
	}
	
	
	public void PayerPorteaPorte(int idCommande,int idUser)
	{
		commandeDao.PayerPorteaPorte(idCommande,idUser);
	}
	

}

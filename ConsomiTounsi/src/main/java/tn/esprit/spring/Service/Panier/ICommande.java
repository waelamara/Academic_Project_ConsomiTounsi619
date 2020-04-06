package tn.esprit.spring.Service.Panier;

import java.util.List;

import tn.esprit.spring.Model.Commande;

public interface ICommande {
	public Commande save (Commande c);
	public List<Commande> CommandeparType(String type);
	public List<Commande> CommandeparClient(int id);
	public List<Commande> findAll();
	public void Delete(Commande c);
	public Commande findOne(Long id);
	public List< Commande> Commandeparcode( long code);
	public void PayerEnLigne(int idCommande);
	public void PayerPorteaPorte(int idCommande);

}

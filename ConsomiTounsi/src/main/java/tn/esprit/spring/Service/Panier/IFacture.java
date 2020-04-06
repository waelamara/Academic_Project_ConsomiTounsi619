package tn.esprit.spring.Service.Panier;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tn.esprit.spring.Model.Facture;
import tn.esprit.spring.Model.lignecommandeproduit;

public interface IFacture {
	public Facture findOne(Long id);
	public List<Facture> findAll();
	public Facture  save ( Facture f);
	public void Delete(Facture f);
	public List<lignecommandeproduit> FactureParIdUser( long id);
	public boolean CreePdf(List<lignecommandeproduit>commandes ,ServletContext context,HttpServletRequest request ,HttpServletResponse reponse ) throws MalformedURLException, IOException;
	
}

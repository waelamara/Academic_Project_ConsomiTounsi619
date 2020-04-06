package tn.esprit.spring.Service.Panier;

import java.util.List;

import tn.esprit.spring.Model.LigneCommande;
import tn.esprit.spring.Model.lignecommandeproduit;

public interface ILigneCommande {
	public LigneCommande findOne(Long id);
	public List<lignecommandeproduit> panierParIdclient( long id);
	public List<LigneCommande> findAll();
	public LigneCommande findLigneCommande(Long idProduit,Long idClient,Long idCommande);
	public List<lignecommandeproduit> AjouterAuPanier(long idprod, long iduser,LigneCommande lc );
	public LigneCommande save(LigneCommande lc);
	public Double PrixTotalCommande(long iduser);

}

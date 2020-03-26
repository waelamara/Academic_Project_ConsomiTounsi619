package tn.esprit.spring.DAO;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.Model.Commande;
import tn.esprit.spring.Model.LigneCommande;
import tn.esprit.spring.Model.User;
import tn.esprit.spring.Model.lignecommandeproduit;
import tn.esprit.spring.Model.GestionProduit.Produit;
import tn.esprit.spring.Repository.CommandeRepository;
import tn.esprit.spring.Repository.LigneCommandeRepository;
import tn.esprit.spring.Repository.ProduitRepository;
import tn.esprit.spring.Repository.UserRepository;


@Service
public class LigneCommandeDao {
	@Autowired
	LigneCommandeRepository ligneCommandeRepository;
	@Autowired
	ProduitRepository produitRepository;
	@Autowired
	CommandeRepository commandeRepository;
	@Autowired
	UserRepository userRepository;
	public List<lignecommandeproduit> panierParIdclient( long id) {
		return ligneCommandeRepository.panierParIdclient(id);

	}
	public LigneCommande findOne(Long id) {
		return ligneCommandeRepository.getOne(id);
	}
	public List<LigneCommande> findAll() {
		return ligneCommandeRepository.findAll();
	}
	public LigneCommande findLigneCommande(Long idProduit,Long idClient){
		return ligneCommandeRepository.findLigneCommande(idProduit, idClient);
	}
	public List<lignecommandeproduit> addBookToCartItem(long idprod, long iduser,LigneCommande lc )
	{
		List<lignecommandeproduit>List=ligneCommandeRepository.panierParIdclient(iduser);
		Produit p = produitRepository.getOne(idprod);
		Commande c=commandeRepository.CommandeencoursparClient(iduser);
		System.out.println(c);
		
		 User cl= userRepository.getOne(iduser);
		 if(List.isEmpty())
		 {
		Commande  c1= new Commande(); 
		c1.setIdUser(cl);
		c1.setDate(LocalDate.now());
		c1.setStatus("en cours");
		c1.setTypedePayment("en cours");
		commandeRepository.save(c1);
		double sum = 0;
		lc.setPrice(p.getPrix());
		 lc.setStatus("en cours");
		 lc.setCommande(c1);
		 lc.setProduit(p);
		 ligneCommandeRepository.save(lc);
		 }
		 else if (c!=null)
		 {
			 lc.setCommande(c);
				lc.setPrice(p.getPrix());
				 lc.setStatus("en cours");
				 lc.setProduit(p);
				 ligneCommandeRepository.save(lc);		 
		 }



		return ligneCommandeRepository.panierParIdclient(iduser) ;
	}
	public LigneCommande save(LigneCommande lc) {
		return ligneCommandeRepository.save(lc);
	}
}
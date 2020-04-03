package tn.esprit.spring.Service.Panier;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.Model.Commande;
import tn.esprit.spring.Model.LigneCommande;
import tn.esprit.spring.Model.User;
import tn.esprit.spring.Model.lignecommandeproduit;
import tn.esprit.spring.Model.Produit.Produit;
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
	public LigneCommande findLigneCommande(Long idProduit,Long idClient,Long idCommande){
		return ligneCommandeRepository.findLigneCommande(idProduit, idClient,idCommande);
	}
	public List<lignecommandeproduit> AjouterAuPanier(long idprod, long iduser,LigneCommande lc )
	{
		List<lignecommandeproduit>List=ligneCommandeRepository.panierParIdclient(iduser);
		System.out.println(List);
		Produit p = produitRepository.getOne(idprod);
		Commande c=commandeRepository.CommandeencoursparClient(iduser);
		LigneCommande l=ligneCommandeRepository.findLigneCommande(idprod, iduser);
		
		
		
	
		 User cl= userRepository.getOne(iduser);
		 if(List.isEmpty())
		 {
			 float total=0;
		Commande  c1= new Commande(); 
		c1.setIdUser(cl);
		c1.setDate(LocalDate.now());
		c1.setStatus("en cours");
		c1.setTypedePayment("en cours");
		lc.setPrice(p.getPrix());
		c1.setMontant(total);
		 lc.setStatus("en cours");
		 lc.setCommande(c1);
		 lc.setProduit(p);
		 ligneCommandeRepository.save(lc);
		 total=(float) (lc.getPrice()*lc.getQuantity());
		c1.setMontant(total);
		 commandeRepository.save(c1);
		 }
		 else if ((c!=null))
		 {
			
				if(l!=null){
					l.setQuantity(l.getQuantity()+1);
					ligneCommandeRepository.save(l);
			 }
				else
				{
			 lc.setCommande(c);
				lc.setPrice(p.getPrix());
				 lc.setStatus("en cours");
				 lc.setProduit(p);
				 ligneCommandeRepository.save(lc);
				}
				double a= PrixTotalCommande(iduser);
				System.out.println(a);
				c.setMontant((float) a);
				commandeRepository.save(c);	 
				} 
			return ligneCommandeRepository.panierParIdclient(iduser) ;
	}
	public Double PrixTotalCommande(long iduser) {
        double sum = 0D;
        List<lignecommandeproduit>List=ligneCommandeRepository.panierParIdclient(iduser);
        for (lignecommandeproduit l : List) {
            sum += l.getTotal();
        }
        return sum;
    }
	public LigneCommande save(LigneCommande lc) {
		return ligneCommandeRepository.save(lc);
	}
}
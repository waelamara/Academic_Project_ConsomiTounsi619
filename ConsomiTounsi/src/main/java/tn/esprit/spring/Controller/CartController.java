package tn.esprit.spring.Controller;

import java.util.List;

import javax.mail.MessagingException;
import javax.transaction.Transactional;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.exception.StripeException;

import tn.esprit.spring.Model.CadeauUser;
import tn.esprit.spring.Model.Commande;
import tn.esprit.spring.Model.Facture;
import tn.esprit.spring.Model.LigneCommande;
import tn.esprit.spring.Model.lignecommandeproduit;
import tn.esprit.spring.Service.Panier.CadeauUserImpl;
import tn.esprit.spring.Service.Panier.CommandeImpl;
import tn.esprit.spring.Service.Panier.FactureImpl;
import tn.esprit.spring.Service.Panier.LigneCommandeImpl;
import tn.esprit.spring.Service.Panier.StripeService;


@Controller(value = "cartController")
@ELBeanName(value = "cartController")
@Join(path = "/Cart", to = "/Cart.jsf")
public class CartController {
	
	@Autowired
	LigneCommandeImpl ligneCommandeDao;
	@Autowired
	CommandeImpl commandeDao;
	@Autowired
	CadeauUserImpl cadeauUserImpl;
	@Autowired
	StripeService stripeService;
	@Autowired
	FactureImpl factureImpl;
	private long id;
	
	private int qty;
	
	private String codeCoupon;
	
	private String carta;
	
	private int expMonth;
	
	private int expYear;
	
	private String cvc;
	
	private Facture facture;
	
	
	
	
	
	public String getCarta() {
		return carta;
	}


	public void setCarta(String carta) {
		this.carta = carta;
	}


	public int getExpMonth() {
		return expMonth;
	}


	public void setExpMonth(int expMonth) {
		this.expMonth = expMonth;
	}


	public int getExpYear() {
		return expYear;
	}
	


	public Facture getFacture() {
		return facture;
	}


	public void setFacture(Facture facture) {
		this.facture = facture;
	}


	public void setExpYear(int expYear) {
		this.expYear = expYear;
	}


	public String getCvc() {
		return cvc;
	}


	public void setCvc(String cvc) {
		this.cvc = cvc;
	}


	public String getCodeCoupon() {
		return codeCoupon;
	}


	public void setCodeCoupon(String codeCoupon) {
		this.codeCoupon = codeCoupon;
	}


	public LigneCommandeImpl getLigneCommandeDao() {
		return ligneCommandeDao;
	}


	public void setLigneCommandeDao(LigneCommandeImpl ligneCommandeDao) {
		this.ligneCommandeDao = ligneCommandeDao;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public int getQty() {
		return qty;
	}


	public void setQty(int qty) {
		this.qty = qty;
	}
	
	
	public List<lignecommandeproduit> panierParIdclient(long id)
	{
		
		return ligneCommandeDao.panierParIdclient(id);
	}
	
public void deleteLigne(long idLigneCommande) {
		
	ligneCommandeDao.deleteLigne(idLigneCommande);
		
}
	public List<Commande> commandeparClient(int id) {
		return commandeDao.CommandeparClient(id);

	}
	
	 public void updateLigne(long idL,int quantity)
	 {
		 
		 ligneCommandeDao.updateLigne(idL,quantity); 
	 }
	
		public void ajouterLigne (Long idprod,Long iduser) 
	    {
		
		    ligneCommandeDao.AjouterAuPanier(idprod,iduser,new LigneCommande(qty));
			
		}
	 
		 public int numProduitPanier(Long iduser)
		 {
			 int a=ligneCommandeDao.numProduitPanier(iduser);
			 System.out.println(ligneCommandeDao.numProduitPanier(iduser));
			 return a;
		 }
		 public int numProduitVendu(Long idProduit)
			{
				return ligneCommandeDao.NumProduitVendu(idProduit);
			}
		 
		 public float montantCadeau(String code,Long idCommande)
		 {
			 float a=cadeauUserImpl.montantCadeau(codeCoupon,idCommande);
			 System.out.println(a);
			 
		 	return cadeauUserImpl.montantCadeau(codeCoupon,idCommande);
		 	
		 }
		 
			public String  Pay( int idUser, String carta1,
			int expMonth1, int expYear1, String cvc1,long idCommande) throws AuthenticationException, InvalidRequestException, CardException, StripeException
				{
				String navigateTo  = "facture.xhtml?faces-redirect=true";
					stripeService.Pay(idUser,carta,expMonth,expYear,cvc);
					
					facture= factureImpl.Ajouter(idCommande);
					return navigateTo;
					
				}
			public Commande commandeencoursparClient(long id)
			{
				return commandeDao.CommandeencoursparClient(id);
			}
			
			public CadeauUser verifier(long idUser)
			{
				return cadeauUserImpl.verifier(idUser);
			}
			public void facturepdf (long id_facture){
				factureImpl.facturepdf(id_facture);
			}
			
			public String cadeauUser(Long idUser) throws MessagingException
			{
				return cadeauUserImpl.CadeauUser(idUser);
			}
			
			

}

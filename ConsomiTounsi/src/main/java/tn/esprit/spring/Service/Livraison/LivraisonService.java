package tn.esprit.spring.Service.Livraison;

import java.time.LocalDate;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.Model.Commande;
import tn.esprit.spring.Model.EMoyenTransportL;
import tn.esprit.spring.Model.Livraison;
import tn.esprit.spring.Model.Livreur;
import tn.esprit.spring.Model.Produit.Produit;
import tn.esprit.spring.Repository.CommandeRepository;
import tn.esprit.spring.Repository.LivraisonRepository;
import tn.esprit.spring.Repository.LivreurRepository;
import tn.esprit.spring.Repository.Produit.ProduitRepository;
import tn.esprit.spring.Service.Livreur.LivreurService;
import tn.esprit.spring.Service.Panier.CommandeImpl;

@Service
public class LivraisonService {
	@Autowired
	LivraisonRepository LivraisonRepository;
	@Autowired
	CommandeRepository CommandeRepository;
	@Autowired
	CommandeImpl CommandeDAO;
	@Autowired
	LivreurRepository LivreurRepository;
	
	@Autowired
	ProduitRepository ProduitRepository;

	private static final Logger L = LogManager.getLogger(LivreurService.class);

	/* Ajouter un livraison */
	public Livraison save(Livraison liv) {
		
		return LivraisonRepository.save(liv);

	}

	/* voir tous les livraisons */
	public List<Livraison> findall() {
		List<Livraison> a = LivraisonRepository.findAll();

		for (Livraison Livraison : a) {
			L.info("Livraison :" + Livraison);

		}
		return a;
	}

	/* Delete D"un Livraison */
	public void delete(long id) {
		LivraisonRepository.deleteById(id);
	}

	/* Update d'un Livraison */
	public Livraison updateLiv(Livraison Liv) {
		return LivraisonRepository.save(Liv);

	}

	/* Chercher un livraison */
	public Livraison findOne(long liv) {
		return LivraisonRepository.getOne(liv);
	}
	
	public void  AjouterLivraison(long id_commande,String Lieux){
		
		Commande c =CommandeRepository.getOne(id_commande);
		Livraison L = new Livraison();
		L.setCommande(c);
		L.setDateAffecLivr(LocalDate.now());
		L.setLieux(Lieux);
		double distance =calculedistance(Lieux);
		System.out.println("athya l distance"+distance);
		if(distance>15)
		{
			L.setMoyenTL(EMoyenTransportL.Voiture);
		
		}
		else{
			L.setMoyenTL(EMoyenTransportL.motorcycle);
		}
		
		L.setEtat(false);
		Livreur DeliveryM = new Livreur();
		DeliveryM=LivreurRepository.findparhasard("accepted","free");
		L.setLivreur(DeliveryM);
		LivraisonRepository.save(L);
	}
	
	
	public float CalculerFraisLivraison (String adresse,long idc)
	{
		double DistanceKm = calculedistance(adresse);
		if (DistanceKm>20){
			double fraisdeliv =(DistanceKm*0.5)+3;
		}
		double fraisdeliv =DistanceKm*0.5;
		float fraisdepoid=CalculCommandepoid(idc) ;
		
		return (float) (fraisdepoid+fraisdeliv);
		
		
		
	}
	
	public float CalculCommandepoid(long idc) {
		float Totalpoid = 0;
		Commande commande = CommandeDAO.findOne(idc);
		long id_commande = commande.getId();
		Produit P = new Produit();

		List<String> Produits = LivraisonRepository.ListProduit_idcommande(id_commande);
		for (String i : Produits) {
            P=ProduitRepository.findById(Long.valueOf(i)).get();
      
			Totalpoid = Totalpoid +P.getPoids();

		}
		float PrixpoidTotal = Totalpoid * 2;
		float prixMoyenT=0;
		float fraistotalliv = PrixpoidTotal+prixMoyenT;
	    
		return fraistotalliv;
		
	}
	
	
	
	
	public double calculedistance(String adresse) {

		double lat = Double.parseDouble(getlatitudelieu(adresse));
		double Long = Double.parseDouble(getlongitudelieu(adresse));
		System.out.println(lat + " aaa " + Long);
		double latlocalAriana = 36.895753;
		double longlocalAriana = 10.18703;
		double R = 6378137;
		double lat_a = convertRad(lat);
		double lon_a = convertRad(Long);
		double lat_b = convertRad(latlocalAriana);
		double lon_b = convertRad(longlocalAriana);
		double D;
		 D = Math.acos((Math.sin(lat_b) * Math.sin(lat_a)) + Math.cos(lat_b) * Math.cos(lat_a) * (Math.cos(lon_a-lon_b)));

		return D*R/1000;
		
	}

	public double convertRad(double input) {
		return (Math.PI * input) / 180;
	}
	
	public String getlatitudelieu(String adresse)
	{
		String map=adresse;
		String[] arrOfStr = map.split(",");
		 String Latitude = arrOfStr[0];
		
		return Latitude;
	}
	
	public String getlongitudelieu(String adresse)
	{
		String map=adresse;
		String[] arrOfStr = map.split(",");
		String Longitude = arrOfStr[1];
		
		return Longitude;
	}
	
	

	
}

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
		//calcule le mindistance et affectation local de livraison
		double distanceAriana=calculedistanceArianna(Lieux);
		double distanceSousse=calculedistanceSousse(Lieux);
		double distanceGabes=calculedistanceGabes(Lieux);
        double Mindistance =Math.min(distanceAriana, Math.min(distanceSousse, distanceGabes));
		System.out.println("athya l distance"+Mindistance);
	    if(Mindistance==distanceAriana){   L.setLocaldistribu("Arianna");}
	    else if (Mindistance==distanceSousse) { 
	    	L.setLocaldistribu("Sousse");
	    }
	    else {L.setLocaldistribu("Gabes"); }
	    //affectation moyen transport
		if(Mindistance>15)
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
		double distanceAriana=calculedistanceArianna(adresse);
		double distanceSousse=calculedistanceSousse(adresse);
		double distanceGabes=calculedistanceGabes(adresse);
		double fraisdeliv;
        double Mindistance =Math.min(distanceAriana, Math.min(distanceSousse, distanceGabes));
		if (Mindistance>15){
			 fraisdeliv =(Mindistance*0.5)+3;
		}
		else{fraisdeliv =Mindistance*0.5;}
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
	
	
	
	
	public double calculedistanceArianna(String adresse) {

		double lat = Double.parseDouble(getlatitudelieu(adresse));
		double Long = Double.parseDouble(getlongitudelieu(adresse));
		double latlocalAriana = 36.895753;
		double longlocalAriana = 10.18703;
		double R = 6378137;
		double lat_a = convertRad(lat);
		double lon_a = convertRad(Long);
		double lat_ariana = convertRad(latlocalAriana);
		double lon_ariana = convertRad(longlocalAriana);
		
		//Calcul distance par rapport local Ariana
		double DA;
		 DA = Math.acos((Math.sin(lat_ariana) * Math.sin(lat_a)) + Math.cos(lat_ariana) * Math.cos(lat_a) * (Math.cos(lon_a-lon_ariana)));
		 double distanceAriana = DA*R/1000;
		 return distanceAriana;
	}
	
	public double calculedistanceSousse (String adresse){
		double lat = Double.parseDouble(getlatitudelieu(adresse));
		double Long = Double.parseDouble(getlongitudelieu(adresse));
		double latlocalSousse =35.828322;
		double longlocalSousse=10.639873;
		double R = 6378137;
		double lat_a = convertRad(lat);
		double lon_a = convertRad(Long);
		double lat_sousse = convertRad(latlocalSousse);
		double lon_sousse = convertRad(longlocalSousse);
		 //Calcul distance par rapport local Sousse
		 double DS;
		 DS = Math.acos((Math.sin(lat_sousse) * Math.sin(lat_a)) + Math.cos(lat_sousse) * Math.cos(lat_a) * (Math.cos(lon_a-lon_sousse)));
		 double distanceSousse = DS*R/1000;
		 return distanceSousse;
		
		
		
	}
	public double calculedistanceGabes (String adresse){
		double lat = Double.parseDouble(getlatitudelieu(adresse));
		double Long = Double.parseDouble(getlongitudelieu(adresse));
		double latlocalGabes=33.883242;
		double longlocalGabes=10.096779;
		double R = 6378137;
		double lat_a = convertRad(lat);
		double lon_a = convertRad(Long);
		double lat_gabes = convertRad(latlocalGabes);
		double lon_gabes = convertRad(longlocalGabes);
		 //33.883242, 10.096779 Calcule distance par rapport local Gabes
		 double DG;
		 DG = Math.acos((Math.sin(lat_gabes) * Math.sin(lat_a)) + Math.cos(lat_gabes) * Math.cos(lat_a) * (Math.cos(lon_a-lon_gabes)));
		 double distanceGabes = DG*R/1000;
		 return distanceGabes;
		
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

package tn.esprit.spring.Controller.GestionUser;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.highfaces.component.api.impl.DefaultChartModel;
import org.highfaces.component.api.impl.DefaultChartSeries;
import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.Model.Chart.EventC;
import tn.esprit.spring.Model.Chart.ReclamtionC;
import tn.esprit.spring.Model.Chart.SexeC;
import tn.esprit.spring.Model.Chart.SujetC;
import tn.esprit.spring.Model.Chart.Top5Product;
import tn.esprit.spring.Model.Produit.Produit;
import tn.esprit.spring.Repository.UserRepository;
import tn.esprit.spring.Repository.reclamationRepository;
import tn.esprit.spring.Repository.Stock.StockRepository;
import tn.esprit.spring.Service.Charite.EventsDAOImpl;
import tn.esprit.spring.Service.GestionUser.ChartService;
import tn.esprit.spring.Service.GestionUser.ComptService;
import tn.esprit.spring.Service.GestionUser.UserService;
import tn.esprit.spring.Service.Produit.ProduitServiceImpl;

@Controller(value = "chartController")
@ELBeanName(value = "chartController")
@Join(path = "/ChartAdmin", to = "/Chart.jsf")
public class ChartController {

	@Autowired
	UserService userService;
	@Autowired
	UserRepository userRep;
	@Autowired
	UserRepository UserRepository;
	@Autowired
	ChartService chartService;
	@Autowired
	reclamationRepository recRep;
	@Autowired
	StockRepository stockRep;
	@Autowired
	EventsDAOImpl eventService;
	@Autowired 
	ProduitServiceImpl prodservice;
	@Autowired
	ComptService comptService;
	protected List<SexeC> sexes ;
	protected List<Top5Product> top5 ;
	protected List<SujetC> sujetc ;
	protected Map<String, List<ReclamtionC>> reclmations;
	protected List<Produit> produits ;
	protected DefaultChartModel model;
	protected List<EventC> evparticip ;
	protected List<EventC> evnbplaces ;
	
	public void getUserSelonSexe()
	{
		sexes = new ArrayList<SexeC>();
		
		sexes.add(new SexeC("Male",userService.getNombresUsersSelonSexe("HOMME")));
		sexes.add(new SexeC("Female",userService.getNombresUsersSelonSexe("FEMME")));		
	}
	public void getTop5Products()
	{
		top5 = new ArrayList<Top5Product>();
		List<Object[]> test=chartService.Top5Produit();
		for(Object[] obj : test){
			top5.add(new Top5Product(String.valueOf(obj[0]), Integer.valueOf(String.valueOf(obj[1]))));
			
			}		
	}
	
	public void getNbSujetByCatg()
	{
		sujetc = new ArrayList<SujetC>();
		List<Object[]> test=chartService.SujetByCategory();
		for(Object[] obj : test){
			sujetc.add(new SujetC(String.valueOf(obj[0]), Integer.valueOf(String.valueOf(obj[1]))));
			
			}		
	}
	public void ReclamtionPerDayEtat()
	{
		List<ReclamtionC> Rec_Rembor = new ArrayList<>();
		List<ReclamtionC> Rec_Echange = new ArrayList<>();
		List<ReclamtionC> Rec_Reparation = new ArrayList<>();
		List<ReclamtionC> Rec_Attente = new ArrayList<>();
		reclmations = new HashMap<>();
		for (int i=0;i<7;i++)
		{
		
		System.out.println(LocalDate.now().plusDays(i).toString());
		Rec_Reparation.add(new ReclamtionC(LocalDate.now().minusDays(i).toString(), recRep.ReclamtionPerDayEtat(i,"En_attente")));
		Rec_Rembor.add(new ReclamtionC(LocalDate.now().minusDays(i).toString(), recRep.ReclamtionPerDayEtat(i,"Remboursement")));

		Rec_Echange.add(new ReclamtionC(LocalDate.now().minusDays(i).toString(), recRep.ReclamtionPerDayEtat(i,"Echange")));
		Rec_Attente.add(new ReclamtionC(LocalDate.now().minusDays(i).toString(), recRep.ReclamtionPerDayEtat(i,"En_attente")));
		}
		reclmations.put("Echange", Rec_Echange);
		reclmations.put("En_attente", Rec_Attente);
		reclmations.put("Remboursement", Rec_Rembor);
		reclmations.put("Réparation", Rec_Reparation);
		
		
	}
	
	public void StockOldNew()
	{
		model = new DefaultChartModel();
		List<Long> listproduits = new ArrayList<>();
		listproduits= stockRep.getListProduitStock();
        DefaultChartSeries NewSeries = new DefaultChartSeries();
        DefaultChartSeries OldSeries = new DefaultChartSeries();
        NewSeries.setName("New Stocks");
        OldSeries.setName("Old Stocks");
        
        for(Long a: listproduits)
        {

        	NewSeries.addPoint(a, stockRep.getProduitStockNew(a));
        	OldSeries.addPoint(a, stockRep.getProduitStockOld(a));
        }
        model.getSeries().add(NewSeries);
        model.getSeries().add(OldSeries);
		
	}
	@Transactional
	public void EventNbpartPlaces()
	{
		List<Long> listevents = new ArrayList<>();
		listevents=userRep.EventList();
		evparticip= new ArrayList<EventC>();
		evnbplaces=new ArrayList<EventC>();
		
		for(Long a: listevents)
        {
			evnbplaces.add(new EventC(eventService.findOne(a).getTitre(), userRep.NombrePlacesEvent(a)));
			evparticip.add(new EventC(eventService.findOne(a).getTitre(), userRep.NombreParticpEvent(a)));
        }
	}
	
	public void getRecentsProducts()
	{
		produits= new ArrayList<Produit>();
		produits=chartService.getLast5Products();
	}
	
	public int getNombrUsers()
	{
		
		return UserRepository.NombreUsers();
	}
	public Long getNombrOrders()
	{
		return chartService.getNombreOrders();
	}
	public float getTotalSales()
	{
		return chartService.getTotalSales();
	}
	public float getTotalDonation()
	{
		return chartService.getTotalDonation();
	}
	

	public float TotalProfitPub()
	{
		return comptService.TotalProfitPub();
	}
	
	public float TotalCouts()
	{
		return comptService.TotalCouts();
	}
	
	
	public float getTotalSalaries()
	{
		return comptService.getTotalSalaries();
	}
	
	public float getBenefices()
	{
		Float x= getTotalSales()+TotalProfitPub()-TotalCouts()-getTotalSalaries();
		return x;
	}

	public List<SexeC> getSexes() {
		return sexes;
	}

	public void setSexes(List<SexeC> sexes) {
		this.sexes = sexes;
	}
	public List<Top5Product> getTop5() {
		return top5;
	}
	public void setTop5(List<Top5Product> top5) {
		this.top5 = top5;
	}
	public List<SujetC> getSujetc() {
		return sujetc;
	}
	public void setSujetc(List<SujetC> sujetc) {
		this.sujetc = sujetc;
	}
	public Map<String, List<ReclamtionC>> getReclmations() {
		return reclmations;
	}
	public void setReclmations(Map<String, List<ReclamtionC>> reclmations) {
		this.reclmations = reclmations;
	}
	public List<Produit> getProduits() {
		return produits;
	}
	public void setProduits(List<Produit> produits) {
		this.produits = produits;
	}
	public DefaultChartModel getModel() {
		return model;
	}
	public void setModel(DefaultChartModel model) {
		this.model = model;
	}
	public List<EventC> getEvparticip() {
		return evparticip;
	}
	public void setEvparticip(List<EventC> evparticip) {
		this.evparticip = evparticip;
	}
	public List<EventC> getEvnbplaces() {
		return evnbplaces;
	}
	public void setEvnbplaces(List<EventC> evnbplaces) {
		this.evnbplaces = evnbplaces;
	}
	
	
	
	
	
}

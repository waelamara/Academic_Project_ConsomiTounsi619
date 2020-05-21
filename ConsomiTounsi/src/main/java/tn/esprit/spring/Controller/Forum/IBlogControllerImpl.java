package tn.esprit.spring.Controller.Forum;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.servlet.http.Part;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import Utils.AppConstants;
import tn.esprit.spring.Model.User;
import tn.esprit.spring.Model.Forum.CategorieSujet;
import tn.esprit.spring.Model.Forum.ImageSujet;
import tn.esprit.spring.Model.Forum.Sujet;
import tn.esprit.spring.Model.Forum.Vus;
import tn.esprit.spring.Service.Forum.ICategorieSujetService;
import tn.esprit.spring.Service.Forum.IImageSujetService;
import tn.esprit.spring.Service.Forum.ISujetService;
import tn.esprit.spring.Service.Forum.IVoteSujetService;
import tn.esprit.spring.Service.Forum.IVusService;
import tn.esprit.spring.Service.Produit.FileStorageServiceImpl;

@Controller(value = "blogController")
@ELBeanName(value = "blogController")
@Join(path = "/blog", to = "/fourm//blog.jsf")
@ViewScoped
public class IBlogControllerImpl{
	@Autowired
	ISujetService iSujetService;
	@Autowired
	 ICategorieSujetService  icategorieSujetService;
	@Autowired 
	IVoteSujetService iVoteSujetService;
	@Autowired
	IImageSujetService iImageSujetService;
	@Autowired
	IVusService iVusService;
	 @Autowired
	  FileStorageServiceImpl fileStorageServiceImpl;
	 
	private RepeatPaginator paginator;
	private RepeatPaginator paginatorRec;
	private Long id;
	private String nomSujet;
	private String description;
	@Temporal (TemporalType.DATE)
    private Date dateAjout;
	private int nbVue;
	private int nbLike;
	private int nbDislike;
	private int nbpoint;
	private List<Sujet> sujets;
	private Sujet sujet;
	private Sujet sujetrec;
	private String nomCategorie;
	private Long categorieId;
	ImageSujet image = new ImageSujet();
	Vus v=new Vus();
	User idUser;
	CategorieSujet idCategorieSujet;
	private Part uploadedFile;
	
	public CategorieSujet getIdCategorieSujet() {
		return idCategorieSujet;
	}
	public void setIdCategorieSujet(CategorieSujet categorieSujet) {
		idCategorieSujet = categorieSujet;
	}
	public User getIdUser() {
		return idUser;
	}
	public void setIdUser(User idUser) {
		this.idUser = idUser;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomSujet() {
		return nomSujet;
	}
	public void setNomSujet(String nomSujet) {
		this.nomSujet = nomSujet;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDateAjout() {
		return dateAjout;
	}
	public void setDateAjout(Date dateAjout) {
		this.dateAjout = dateAjout;
	}
	public int getNbVue() {
		return nbVue;
	}
	public void setNbVue(int nbVue) {
		this.nbVue = nbVue;
	}
	public int getNbLike() {
		return nbLike;
	}
	public void setNbLike(int nbLike) {
		this.nbLike = nbLike;
	}
	public int getNbDislike() {
		return nbDislike;
	}
	public void setNbDislike(int nbDislike) {
		this.nbDislike = nbDislike;
	}
	public int getNbpoint() {
		return nbpoint;
	}
	public void setNbpoint(int nbpoint) {
		this.nbpoint = nbpoint;
	}
	

	

	public RepeatPaginator getPaginatorRec() {
		return paginatorRec;
	}
	public void setPaginatorRec(RepeatPaginator paginatorRec) {
		this.paginatorRec = paginatorRec;
	}
	public String getNomCategorie() {
		return nomCategorie;
	}
	public void setNomCategorie(String nomCategorie) {
		this.nomCategorie = nomCategorie;
	}
	
	
	public Long getCategorieId() {
		return categorieId;
	}
	public void setCategorieId(Long categorieId) {
		this.categorieId = categorieId;
	}
	
	public Part getUploadedFile() {
		return uploadedFile;
	}
	public void setUploadedFile(Part uploadedFile) {
		this.uploadedFile = uploadedFile;
	}
	/********show all sujets****/
	public List<Sujet> getAllSujets() {
		 List<Sujet> sujets =iSujetService.getAllSujets();
		for (Sujet s:sujets){
			iVoteSujetService.affecterdespoints(s.getId());
			iVusService.countVus(s.getId());
		}
		
		return iSujetService.getAllSujets();
	}
	/*******show Sujet By User******/
	
	
	public List<Sujet>getSujetByUser(Long userId){
		return iSujetService.findSujetbyUser(userId);	
		
	}
	
	/******pagination****/
	@PostConstruct
//	@Scheduled(cron="*/10 * * * * *")
	public void init(){
	List <Sujet> s= getAllSujets();
	paginator = new RepeatPaginator(s);
	
	}

    public RepeatPaginator getPaginator() {
    	 
        return paginator;
    }
	
	public Sujet getSujet() {
		return sujet;
	
	}
	public void setSujet(Sujet sujet) {
		this.sujet = sujet;
	}
	
  String a;
	private String getCountryFromJSF(FacesContext context) {
        Map<String, String> parameters = context.getExternalContext().getRequestParameterMap();
        return parameters.get("idsujet");
    }
	 public Long outcome() {
	        FacesContext context = FacesContext.getCurrentInstance();
	        a = getCountryFromJSF(context);
	        System.out.println(a);
	        return Long.parseLong(a);
	        
	    }

	
	public Sujet getSujetrec() {
		return sujetrec;
	}
	public Sujet findSujetrec() {
		return sujetrec= iSujetService.findOne(outcome());
	}
	public void setSujetrec(Sujet sujetrec) {
		this.sujetrec = sujetrec;
	}
	
	
	/*******convertion  dates******/
	public String convertireDate(Date D){
		SimpleDateFormat formatter = new SimpleDateFormat("dd MMM, yyyy");
		return formatter.format(D);
	}
	public String convertireTime(Date d){
	SimpleDateFormat  formatter = new SimpleDateFormat(" MMMM d, yyyy 'at' HH:mm a "); 
	return formatter.format(d);
}    
	/*******addPost********/
	public String ajouterSujet(Long userId){
		String navigateTo =null;
		long cc=getCategorieId();
		System.out.println("********"+cc);
		Sujet s=new Sujet(nomSujet,description);
		iSujetService.ajouterSujet(s,cc, userId);
		String newFileName=fileStorageServiceImpl.UploadImage(uploadedFile);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path(AppConstants.DOWNLOAD_PATH)
		.path(newFileName).toUriString();
		image.setImage(fileDownloadUri);
		System.out.println("********"+fileDownloadUri);
		image.setSujetId(s);
		iImageSujetService.ajouterImage(image);
		return navigateTo;
	}
/*****add view*****/

	   public void ajouterVus(Long userId, Long sujetId){
		   
		if(iVusService.verificationVus(userId, sujetId)){
			iVusService.UpdateVus(sujetId, userId);
		}else{
		iVusService.ajouterVus(v, sujetId, userId);}
	   }

	

}
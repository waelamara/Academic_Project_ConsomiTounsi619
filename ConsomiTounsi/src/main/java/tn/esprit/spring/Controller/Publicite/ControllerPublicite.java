package tn.esprit.spring.Controller.Publicite;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.Model.Publicite.Canal;
import tn.esprit.spring.Model.Publicite.GenderCible;
import tn.esprit.spring.Model.Publicite.Publicite;
import tn.esprit.spring.Service.Publicite.IPubliciteService;

@Controller(value = "ControllerPublicite")
@ELBeanName(value = "ControllerPublicite")
@Join(path = "/FromPublicite", to = "/pages/PublicityForm.jsf")
public class ControllerPublicite {
	@Autowired
	IPubliciteService iPubliciteService;
	
	private String nom;
	private int debutAgeCible=15;
	private int finAgeCible=70;
	private GenderCible genderCible;
	private Date dateDebut;
	private String dateDebutString;
	private Date dateFin;
	private String dateFinString;
	private Canal canal;
	private UploadedFile file;
	private String fileToUpdate;
	private int nbrInitialVueCible;
	private int nbrFinalVue;
	private float cout;
	private String video;
	private String image;
	private String nomRecherchePub;
	private String searchByCanal;
	private Long idToUpdate;
	private float coutDispaly;
	private String typeFile="Image";
	
	
	
	public String addPublicite() throws IOException, ParseException {

        this.dateDebut=iPubliciteService.ConvertirDate(dateDebutString);
        this.dateFin=iPubliciteService.ConvertirDate(dateFinString);
		if(idToUpdate!=null && file.getSize()==0){
			iPubliciteService.UpdatePubWithoutImage(new Publicite( idToUpdate,nom,  canal,  genderCible,dateDebut,dateFin,  debutAgeCible,finAgeCible,video,image));
			HidePub();
			idToUpdate=null;
			 return "/pages/AllPublicities.xhtml?faces-redirect=true";
		}
		else if(idToUpdate!=null && file.getSize()!=0)
		{
			iPubliciteService.AddPub(new Publicite( idToUpdate,nom,  canal,  genderCible,dateDebut,dateFin,  debutAgeCible,finAgeCible), file);
			HidePub();
			idToUpdate=null;
			 return "/pages/AllPublicities.xhtml?faces-redirect=true";
		}
		
		else{
		iPubliciteService.AddPub(new Publicite( nom,  canal,  genderCible,dateDebut,dateFin,  debutAgeCible,finAgeCible), file);
		HidePub();
		 return "/pages/AllPublicities.xhtml?faces-redirect=true";
		}
	}
	
	
	public List<Publicite> getAllPub(){
		if (nomRecherchePub==null ){
		return iPubliciteService.findAll();}
		else {
			return iPubliciteService.findLikeName(nomRecherchePub);
		}
	}
	
	public void HidePub(){
		this.debutAgeCible=15;
		this.finAgeCible=70;
		this.nom=null;
		this.canal=null;
		this.file=null;
		this.genderCible=null;
		this.dateFinString=null;
		this.dateDebutString=null;
		this.image=null;
		this.video=null;
		this.typeFile="Image";
	}
	
	public void removePub(Long idPub){
		iPubliciteService.Delete(idPub);
	}
	
	
	
	public String DisplayPublicite(Publicite p){
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");  
		this.idToUpdate=p.getId();
		this.nom=p.getNom();
		this.canal=p.getCanal();
		this.dateDebutString=dateFormat.format(p.getDateDebut());
		this.dateFinString=dateFormat.format(p.getDateFin());
		this.genderCible=p.getGenderCible();
		this.debutAgeCible=p.getDebutAgeCible();
		this.finAgeCible=p.getFinAgeCible();
		this.video=p.getVideo();	
		this.image=p.getImage();
		this.nbrFinalVue=p.getNbrFinalVue();
		if(p.getImage()!=null) this.typeFile="Image";
		else if(p.getVideo()!=null) this.typeFile="Video";
		return "/pages/PublicityForm.xhtml?faces-redirect=true";
	}
	
	public float UpdateCoutDispaly() throws ParseException, IOException {
		

		if(dateDebutString!=null && dateFinString!=null)
		{
	        this.dateDebut=iPubliciteService.ConvertirDate(dateDebutString);
	        this.dateFin=iPubliciteService.ConvertirDate(dateFinString);
			return iPubliciteService.CalculeCoutTotalPub(genderCible.toString(), canal.toString(), debutAgeCible, finAgeCible, dateDebut.toString(), dateFin.toString(), typeFile);
		}
		else return 0;
	
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public String getDateDebutString() {
		return dateDebutString;
	}



	public void setDateDebutString(String dateDebutString) {
		this.dateDebutString = dateDebutString;
	}

	public String getDateFinString() {
		return dateFinString;
	}

	public void setDateFinString(String dateFinString) {
		this.dateFinString = dateFinString;
	}

	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getDebutAgeCible() {
		return debutAgeCible;
	}
	public void setDebutAgeCible(int debutAgeCible) {
		this.debutAgeCible = debutAgeCible;
	}
	public int getFinAgeCible() {
		return finAgeCible;
	}
	public void setFinAgeCible(int finAgeCible) {
		this.finAgeCible = finAgeCible;
	}
	public GenderCible getGenderCible() {
		return genderCible;
	}
	public void setGenderCible(GenderCible genderCible) {
		this.genderCible = genderCible;
	}
	public Date getDateDebut() {
		return dateDebut;
	}
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}
	public Date getDateFin() {
		return dateFin;
	}
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}
	public Canal getCanal() {
		return canal;
	}
	public void setCanal(Canal canal) {
		this.canal = canal;
	}
	
	public Canal[] getCanals() {
		return Canal.values();
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}


	public int getNbrInitialVueCible() {
		return nbrInitialVueCible;
	}


	public void setNbrInitialVueCible(int nbrInitialVueCible) {
		this.nbrInitialVueCible = nbrInitialVueCible;
	}


	public int getNbrFinalVue() {
		return nbrFinalVue;
	}


	public void setNbrFinalVue(int nbrFinalVue) {
		this.nbrFinalVue = nbrFinalVue;
	}


	public float getCout() {
		return cout;
	}


	public void setCout(float cout) {
		this.cout = cout;
	}


	public String getVideo() {
		return video;
	}


	public void setVideo(String video) {
		this.video = video;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	public String getNomRecherchePub() {
		return nomRecherchePub;
	}


	public void setNomRecherchePub(String nomRecherchePub) {
		this.nomRecherchePub = nomRecherchePub;
	}


	public String getSearchByCanal() {
		return searchByCanal;
	}


	public void setSearchByCanal(String searchByCanal) {
		this.searchByCanal = searchByCanal;
	}


	public Long getIdToUpdate() {
		return idToUpdate;
	}


	public void setIdToUpdate(Long idToUpdate) {
		this.idToUpdate = idToUpdate;
	}


	public String getFileToUpdate() {
		return fileToUpdate;
	}


	public void setFileToUpdate(String fileToUpdate) {
		this.fileToUpdate = fileToUpdate;
	}




	public float getCoutDispaly() {
		return coutDispaly;
	}


	public void setCoutDispaly(float coutDispaly) {
		this.coutDispaly = coutDispaly;
	}


	public String getTypeFile() {
		return typeFile;
	}


	public void setTypeFile(String typeFile) {
		this.typeFile = typeFile;
	}
	
	
	
}

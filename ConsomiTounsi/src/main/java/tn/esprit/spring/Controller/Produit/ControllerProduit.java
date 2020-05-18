package tn.esprit.spring.Controller.Produit;


import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.Model.Produit.Produit;
import tn.esprit.spring.Repository.Produit.ProduitRepository;
import tn.esprit.spring.Service.Produit.ICategorieService;
import tn.esprit.spring.Service.Produit.IProduitService;
import tn.esprit.spring.Service.Produit.ISousCategorieService;
import tn.esprit.spring.Service.Produit.ISousSousCategorieService;


@Controller(value = "ControllerProduit")
@ELBeanName(value = "ControllerProduit")
@Join(path = "/produit", to = "/pages/produit.jsf")
public class ControllerProduit {
	@Autowired
	IProduitService iproduitService;
	@Autowired
	ICategorieService iCategorieService;
	@Autowired
	ISousCategorieService iSousCategorieService;
	@Autowired
	ISousSousCategorieService iSousSousCategorieService;
	@Autowired
	ProduitRepository produitRepository;
	
	
	private Long id;
	private String nomProduit;
	private float prix;
	private String description;
	private Long barcode;
	private float poids;
	private float prixAchat;
	private int filtrageProduit;
	private Long idFiltrageProduit;
	private UploadedFile file;
	private Part image;

	public List<Produit> getProduitsByCategorie(Long idCategorie){
		return iproduitService.findProduitCategorie(idCategorie);
	}
	
	public List<Produit> getProduitsBySCategorie(Long idSCategorie){
		return iproduitService.findProduitSCategorie(idSCategorie);
	}
	public List<Produit> getProduitsBySsCategorie(Long idSsCategorie){
		return iproduitService.findProduitSsCategorie(idSsCategorie); 
	}
	
	@Transactional
	public String nameRecherche(){
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		setFiltrageProfuit(Integer.parseInt(params.get("filtrageProduit")));
		setIdFiltrageProfuit(Long.parseLong(params.get("idRecherhceProduit")));
		if(filtrageProduit==0){
			return iCategorieService.findOne(idFiltrageProduit).getNomCategorie();
		}
		else if(filtrageProduit==1){
			return iSousCategorieService.findOne(idFiltrageProduit).getNomSCategorie();
			}
		else if(filtrageProduit==2){
			return iSousSousCategorieService.findOne(idFiltrageProduit).getNomSsCategorie();}
		else return null;
	}
	
	public Produit getOneProduit(){
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		return produitRepository.findProduit(Long.parseLong(params.get("idProduit")));
	}
	


	public List<Produit> getProduits(){
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		setFiltrageProfuit(Integer.parseInt(params.get("filtrageProduit")));
		setIdFiltrageProfuit(Long.parseLong(params.get("idRecherhceProduit")));
		if(filtrageProduit==0){
			return iproduitService.findProduitCategorie(idFiltrageProduit);	
		}
		else if(filtrageProduit==1){
			return iproduitService.findProduitSCategorie(idFiltrageProduit);
			}
		else if(filtrageProduit==2){
			return iproduitService.findProduitSsCategorie(idFiltrageProduit);}
		else return null;
	}
	
	
	
    public void upload() {
    	System.out.println(image);
    	System.out.println("Image Added");
        /*if (file != null) {
            FacesMessage message = new FacesMessage("Successful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            
        }*/
    }
	
	
	
	public void addProduit() {
		System.out.println(file);
		iproduitService.addProduitWithOutImage(new Produit( nomProduit,  prix,  description,  barcode,  poids,  prixAchat));
	}
	
	
	public void handleFileUpload(FileUploadEvent event) {
		System.out.println(event.getFile());
		System.out.println("aaaaaaaaaaaaaaa");
		file = event.getFile();
	}
	
	
	
	
	public void doUpload(){
		System.out.println(image);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	public void uploadMultiple() {
//        if (files != null) {
//            for (UploadedFile f : files.getFiles()) {
//                FacesMessage message = new FacesMessage("Successful", f.getFileName() + " is uploaded.");
//                FacesContext.getCurrentInstance().addMessage(null, message);
//            }
//        }
//    }
//	

	

	
	
//	 public void upload(FileUploadEvent event) {
//	        FacesMessage msg = new FacesMessage("Success! ", event.getFile().getFileName() + " is uploaded.");
//	        FacesContext.getCurrentInstance().addMessage(null, msg);
//	        // Do what you want with the file
//	        try {
//	        	System.out.println(event.getFile().getFileName());
//	            copyFile(event.getFile().getFileName(), event.getFile().getInputStream());
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	        }
//	 
//	    }
	
	
//	 public void copyFile(String fileName, InputStream in) {
//	        try {
//	 
//	            // write the inputStream to a FileOutputStream
//	            OutputStream out = new FileOutputStream(new File(destination + fileName));
//	 
//	            int read = 0;
//	            byte[] bytes = new byte[1024];
//	 
//	            while ((read = in.read(bytes)) != -1) {
//	                out.write(bytes, 0, read);
//	            }
//	 
//	            in.close();
//	            out.flush();
//	            out.close();
//	 
//	            System.out.println("New file created!");
//	        } catch (IOException e) {
//	            System.out.println(e.getMessage());
//	        }
//	    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	



	public Part getImage() {
		return image;
	}

	public void setImage(Part image) {
		this.image = image;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomProduit() {
		return nomProduit;
	}

	public void setNomProduit(String nomProduit) {
		this.nomProduit = nomProduit;
	}

	public float getPrix() {
		return prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getBarcode() {
		return barcode;
	}

	public void setBarcode(Long barcode) {
		this.barcode = barcode;
	}

	public float getPoids() {
		return poids;
	}

	public void setPoids(float poids) {
		this.poids = poids;
	}

	public float getPrixAchat() {
		return prixAchat;
	}

	public void setPrixAchat(float prixAchat) {
		this.prixAchat = prixAchat;
	}

	public int getFiltrageProfuit() {
		return filtrageProduit;
	}

	public void setFiltrageProfuit(int filtrageProduit) {
		this.filtrageProduit = filtrageProduit;
	}

	public Long getIdFiltrageProfuit() {
		return idFiltrageProduit;
	}

	public void setIdFiltrageProfuit(Long idFiltrageProduit) {
		this.idFiltrageProduit = idFiltrageProduit;
	}


	

}

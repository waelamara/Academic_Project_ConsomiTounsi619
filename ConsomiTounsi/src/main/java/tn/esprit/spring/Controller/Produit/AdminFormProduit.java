package tn.esprit.spring.Controller.Produit;

import javax.servlet.http.Part;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import Utils.AppConstants;
import tn.esprit.spring.Service.Produit.FileStorageServiceImpl;

@Controller(value = "AdminFormProduit")
@ELBeanName(value = "AdminFormProduit")
@Join(path = "/ajouterProduit", to = "/pages/AdminFormProduit.jsf")
public class AdminFormProduit {
	@Autowired
	FileStorageServiceImpl fileStorageServiceImpl;
	
	private Part uploadedFile;

	public Part getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(Part uploadedFile) {
		this.uploadedFile = uploadedFile;
	}
	public void saveFile(){
			String newFileName=fileStorageServiceImpl.UploadImage(uploadedFile);
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
					.path(AppConstants.DOWNLOAD_PATH).path(newFileName).toUriString();
			System.out.println(fileDownloadUri);	
	}
}

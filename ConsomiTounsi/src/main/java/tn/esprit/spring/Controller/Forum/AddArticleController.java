package tn.esprit.spring.Controller.Forum;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import Utils.AppConstants;
import tn.esprit.spring.Service.Produit.FileStorageServiceImpl;

@Controller(value = "addArticleController")
@ELBeanName(value = "addArticleController")
@Join(path = "/add-article", to = "/fourm/add-article.jsf")
public class AddArticleController {

	private Part uploadedFile;
	@Autowired
	FileStorageServiceImpl fileStorageServiceImpl;
	public Part getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(Part uploadedFile) {
		this.uploadedFile = uploadedFile;
	}
	
		public void saveFile(){
			String newFileName=fileStorageServiceImpl.UploadImage(uploadedFile);
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path(AppConstants.DOWNLOAD_PATH)
			.path(newFileName).toUriString();
			System.out.println(fileDownloadUri);
}
			
			
}

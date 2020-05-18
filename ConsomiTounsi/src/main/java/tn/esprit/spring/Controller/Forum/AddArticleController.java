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
	private String folder = "C:\\files";

	public Part getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(Part uploadedFile) {
		this.uploadedFile = uploadedFile;
	}
	
		public void saveFile(){
			
			try (InputStream input = uploadedFile.getInputStream()) {
				String fileName = uploadedFile.getSubmittedFileName();
				String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
						.path(AppConstants.DOWNLOAD_PATH).path(fileName).toUriString();
				System.out.println(fileDownloadUri);
				System.out.println(fileName+" "+"aaaaaaaaa");
		        Files.copy(input, new File(folder, fileName).toPath());
		    }
		    catch (IOException e) {
		        e.printStackTrace();
		    }
		}
}

	
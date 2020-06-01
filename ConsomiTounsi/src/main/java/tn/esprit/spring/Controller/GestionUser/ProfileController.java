package tn.esprit.spring.Controller.GestionUser;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.primefaces.model.file.UploadedFile;
import org.primefaces.model.file.UploadedFiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import Utils.AppConstants;
import tn.esprit.spring.Model.ImageUser;
import tn.esprit.spring.Model.User;
import tn.esprit.spring.Service.GestionUser.IImageUserService;
import tn.esprit.spring.Service.GestionUser.UserService;
import tn.esprit.spring.Service.Produit.FileStorageServiceImpl;

@Controller(value = "profileController")
@ELBeanName(value = "profileController")
@Join(path = "/profil", to = "/profil.jsf")
@Component
public class ProfileController {
	@Autowired
	UserService userService;
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	IImageUserService iImageUserService;
	@Autowired
	FileStorageServiceImpl fileStorageServiceImpl;
	
	private String Password;
	private String VerifyPass;
	private UploadedFiles file;
	
	
	
	public String updateUser()
	{
		User u = new User();
		u=userService.findOne(LoginController.userDetails.getId());
		u.setFirstName(LoginController.userDetails.getFirstName());
		u.setLastName(LoginController.userDetails.getLastName());
		u.setAddress(LoginController.userDetails.getAddress());
		u.setEmail(LoginController.userDetails.getEmail());
		u.setTel(LoginController.userDetails.getTel());
		u.setDateN(LoginController.userDetails.getDateN());
		userService.updateUser(u);
		FacesMessage facesMessage =

				new FacesMessage("User Updated with Sucess");

				FacesContext.getCurrentInstance().addMessage("form1:btn",facesMessage);
		return "profil.xhtml?faces-redirect=true";
		
	}
	
	public String updatePassword()
	{
		User u = new User();
		u=userService.findOne(LoginController.userDetails.getId());
		if(Password.equals(VerifyPass))
		{
			u.setPassword(encoder.encode(Password));
			userService.updateUser(u);
			FacesMessage facesMessage =

					new FacesMessage("Password Updated with Sucess");

					FacesContext.getCurrentInstance().addMessage("form2:btn1",facesMessage);
		}
		else
		{
			FacesMessage facesMessage =

					new FacesMessage("Password not match!");

					FacesContext.getCurrentInstance().addMessage("form2:btn1",facesMessage);
		}
		
		return "profil.xhtml?faces-redirect=true";
		
	}
	
	public String updateProfileImage()
	{
		ImageUser image = new ImageUser();
		User u = new User();
		u=userService.findOne(LoginController.userDetails.getId());
		for (UploadedFile f : file.getFiles()) {
         	String newFileName = fileStorageServiceImpl.UploadImages(f);
         	String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path(AppConstants.DOWNLOAD_PATH).path(newFileName).toUriString();
			
			image = iImageUserService.findImageUser(u.getId());
			
			image.setImage(fileDownloadUri);
			iImageUserService.ajouterImage(image);
		}
		
		return "profil.xhtml?faces-redirect=true";
		
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getVerifyPass() {
		return VerifyPass;
	}

	public void setVerifyPass(String verifyPass) {
		VerifyPass = verifyPass;
	}

	public UploadedFiles getFile() {
		return file;
	}

	public void setFile(UploadedFiles file) {
		this.file = file;
	}

	
}

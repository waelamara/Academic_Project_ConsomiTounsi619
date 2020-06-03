package tn.esprit.spring.Controller.GestionUser;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.primefaces.model.file.UploadedFile;
import org.primefaces.model.file.UploadedFiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import Utils.AppConstants;
import tn.esprit.spring.Model.ERole;
import tn.esprit.spring.Model.ImageUser;
import tn.esprit.spring.Model.Role;
import tn.esprit.spring.Model.Sexe;
import tn.esprit.spring.Model.User;
import tn.esprit.spring.Model.Produit.ImageProduit;
import tn.esprit.spring.Repository.RoleRepository;
import tn.esprit.spring.Repository.UserRepository;
import tn.esprit.spring.Service.Forum.IImageSujetService;
import tn.esprit.spring.Service.GestionUser.IImageUserService;
import tn.esprit.spring.Service.GestionUser.OnRegistrationCompleteEvent;
import tn.esprit.spring.Service.GestionUser.UserService;
import tn.esprit.spring.Service.Produit.FileStorageServiceImpl;
import tn.esprit.spring.payload.response.MessageResponse;

@Controller(value = "signupController")
@ELBeanName(value = "signupController")
@Join(path = "/signup", to = "/signup.jsf")
@Component
public class SignupController {
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	UserService userService;

	@Autowired
	PasswordEncoder encoder;
	@Autowired
	ApplicationEventPublisher eventPublisher;
	
	@Autowired
	IImageUserService iImageUserService;
	@Autowired
	FileStorageServiceImpl fileStorageServiceImpl;
	
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private String email;
	private String address;
    private Date dateN;
	private String tel;
 	private Sexe sexe;
 	private Sexe[] sexelist;
 	private UploadedFiles file;
 	
 	public Sexe[] getSexeList()
 	{
 		return sexe.values();
 	}

	public SignupController() {
		try
		{
		System.out.println(LoginController.userDetails.getFirstName());
		} catch(Exception e){
			System.out.println("Error, please connect to get acces to user details");
		}
	}


	public String doSignup() throws IOException {
		String navigateTo = "null";
		if (userRepository.existsByUsername(username)) {
			FacesMessage facesMessage =

					new FacesMessage("Error: Username is already taken!");

					FacesContext.getCurrentInstance().addMessage("form1:btn",facesMessage);
		}

		else if (userRepository.existsByEmail(email)) {
			FacesMessage facesMessage =

					new FacesMessage("Error: Email is already in use!");

					FacesContext.getCurrentInstance().addMessage("form1:btn",facesMessage);
		}
		else
		{
			User user = new User(username, 
					 email,
					 encoder.encode(password),firstName
					 ,lastName,address,dateN,tel
					 ,sexe);
			Set<Role> roles = new HashSet<>();
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			
			roles.add(userRole);
			user.setRoles(roles);
			userRepository.save(user);
			
			for (UploadedFile f : file.getFiles()) {
	         	String newFileName = fileStorageServiceImpl.UploadImages(f);
	         	String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path(AppConstants.DOWNLOAD_PATH).path(newFileName).toUriString();
				
				ImageUser image = new ImageUser();
				image.setImage(fileDownloadUri);
				image.setUserId(user);
				iImageUserService.ajouterImage(image);
			}
			
			String appUrl = "";
			User registered= user;
			eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, appUrl));
			navigateTo="login.xhtml?faces-redirect=true";
			FacesMessage facesMessage =

					new FacesMessage("Registered successfully, please verify your account!");

					FacesContext.getCurrentInstance().addMessage("form:btn",facesMessage);
		}
		return navigateTo;
		
	}
	
	


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public Date getDateN() {
		return dateN;
	}


	public void setDateN(Date dateN) {
		this.dateN = dateN;
	}


	public String getTel() {
		return tel;
	}


	public void setTel(String tel) {
		this.tel = tel;
	}


	public Sexe getSexe() {
		return sexe;
	}


	public void setSexe(Sexe sexe) {
		this.sexe = sexe;
	}


	public UploadedFiles getFile() {
		return file;
	}


	public void setFile(UploadedFiles file) {
		this.file = file;
	}


	
	
	
	

}

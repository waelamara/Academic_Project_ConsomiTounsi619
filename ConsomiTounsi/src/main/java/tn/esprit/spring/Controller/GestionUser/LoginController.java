package tn.esprit.spring.Controller.GestionUser;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import tn.esprit.spring.Model.User;
import tn.esprit.spring.Repository.RoleRepository;
import tn.esprit.spring.Repository.UserRepository;
import tn.esprit.spring.Service.GestionUser.UserService;
import tn.esprit.spring.security.jwt.JwtUtils;
import tn.esprit.spring.security.oauth2.HttpCookieOAuth2AuthorizationRequestRepository;
import tn.esprit.spring.security.services.UserDetailsImpl;


@Controller(value = "loginController")
@ELBeanName(value = "loginController")
@Join(path = "/login", to = "/login.jsf")
@Component
public class LoginController extends SimpleUrlAuthenticationSuccessHandler{
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserService userService;

	@Autowired
	RoleRepository roleRepository;
	

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;
	
	private String login; 
	private String password;
	private Boolean loggedIn=false;
	public static UserDetailsImpl userDetails;
	private Authentication authentication;
	public static LoginController instance; 
	
    private HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;

    

	public LoginController(
			HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository) {
		this.httpCookieOAuth2AuthorizationRequestRepository = httpCookieOAuth2AuthorizationRequestRepository;
	}

	@Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        userDetails=(UserDetailsImpl) authentication.getPrincipal();
        System.out.println(userDetails.getFirstName());
    }

	public UserDetailsImpl getUserDetails() {
		return userDetails;
	}


	public void setUserDetails(UserDetailsImpl userDetails) {
		this.userDetails = userDetails;
	}


	public Authentication getAuthentication() {
		return authentication;
	}


	public void setAuthentication(Authentication authentication) {
		this.authentication = authentication;
	}


	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Boolean getLoggedIn() {
		return loggedIn;
	}


	public void setLoggedIn(Boolean loggedIn) {
		this.loggedIn = loggedIn;
	}


	public String doLogin() {
		
		String navigateTo = "null";
		try
		{
		 authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(login, password));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		userDetails = (UserDetailsImpl) authentication.getPrincipal();
		User U = userRepository.findByUsername(login)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + login));
		
System.out.println("Role"+U.getRoles().stream().findFirst().get().getId());
		if(authentication.isAuthenticated())
		{
			if (!userDetails.getEtatAcc()) {
				FacesMessage facesMessage =

						new FacesMessage("Error: Your account is Disabled by Admin!");

						FacesContext.getCurrentInstance().addMessage("form:btn",facesMessage);
			}
			
			else if (!U.isEnabled()) {
				FacesMessage facesMessage =

						new FacesMessage("Please Confirm your Account, We've sent you a confirmation email");

						FacesContext.getCurrentInstance().addMessage("form:btn",facesMessage);
			}
			else if(U.getRoles().stream().findFirst().get().getId()==1)
			{
				navigateTo = "acceuil.xhtml?faces-redirect=true";
			loggedIn = true; 
			}
			else if(U.getRoles().stream().findFirst().get().getId()==2)
			{
				navigateTo = "Chart.xhtml?faces-redirect=true";
			loggedIn = true; 
			}
			else if(U.getRoles().stream().findFirst().get().getId()==4)
			{
				navigateTo = "LoginDeliveryController.xhtml?faces-redirect=true";
				userDetails=null;
				loggedIn = false; 
			}
			else
			{
				
			}
			
		}
		}
		catch (BadCredentialsException badCredentialsException)  {
			FacesMessage facesMessage =

					new FacesMessage("Login Failed: please check your username/password and try again.");

					FacesContext.getCurrentInstance().addMessage("form:btn",facesMessage);
		}
		catch (UsernameNotFoundException e) {
			FacesMessage facesMessage =

					new FacesMessage("Login Failed: please check your username/password and try again.");

					FacesContext.getCurrentInstance().addMessage("form:btn",facesMessage);
		}	
		return navigateTo;
		}
	
	
	///////////////
	
	public String doLogout() {
		userDetails=null;
		loggedIn=false;
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/login.xhtml?faces-redirect=true";
		}

}

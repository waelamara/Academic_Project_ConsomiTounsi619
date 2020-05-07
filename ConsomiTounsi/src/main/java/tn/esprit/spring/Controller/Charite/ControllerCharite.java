package tn.esprit.spring.Controller.Charite;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import tn.esprit.spring.Model.Charite.Charite;
import tn.esprit.spring.Service.Charite.ChariteDAO;
import tn.esprit.spring.security.services.UserDetailsImpl;

@Controller(value = "ControllerCharite")
@ELBeanName(value = "ControllerCharite")
public class ControllerCharite {
	@Autowired
	ChariteDAO chariteDAO;
	private Long id; 
	private String typeCharite;
	private float montantPaye;
	
	public List<Charite> getAllCharite() {
		return chariteDAO.getAllChariteList();
	}
	public List<Charite> getAllChariteUser(Authentication authentication,Charite C) {
		List<Charite> com=new ArrayList<>();
		UserDetailsImpl u1 = (UserDetailsImpl) authentication.getPrincipal();
		com = chariteDAO.getCharite(u1.getId());
		return com;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTypeCharite() {
		return typeCharite;
	}
	public void setTypeCharite(String typeCharite) {
		this.typeCharite = typeCharite;
	}
	public float getMontantPaye() {
		return montantPaye;
	}
	public void setMontantPaye(float montantPaye) {
		this.montantPaye = montantPaye;
	}
	

}

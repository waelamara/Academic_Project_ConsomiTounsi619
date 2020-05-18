package tn.esprit.spring.Controller.Charite;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.ocpsoft.rewrite.annotation.Join;
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
@Join(path = "/showCharite", to = "/Charity.jsf")
public class ControllerCharite {
	@Autowired
	ChariteDAO chariteDAO;
	
	
	public List<Charite> getAllCharite() {
		return chariteDAO.getAllChariteList();
	}
	public List<Charite> getAllChariteUser(Authentication authentication,Charite C) {
		List<Charite> com=new ArrayList<>();
		UserDetailsImpl u1 = (UserDetailsImpl) authentication.getPrincipal();
		com = chariteDAO.getCharite(u1.getId());
		return com;
	}
	
	
	

}

package tn.esprit.spring.Controller.Forum;

import java.util.Map;

import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.Model.Forum.ImageSujet;
import tn.esprit.spring.Service.Forum.IImageSujetService;

@Controller(value = "ControllerImagesSujet")
@ELBeanName(value = "ControllerImagesSujet")
public class IImagesSujetControllerImpl {
	@Autowired
	IImageSujetService imagesujetservice;
	
	private Long id;
	private String image;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}

	  String a;
		private String getCountryFromJSF(FacesContext context) {
	        Map<String, String> parameters = context.getExternalContext().getRequestParameterMap();
	        return parameters.get("idsujet");
	    }
		 public Long outcome() {
		        FacesContext context = FacesContext.getCurrentInstance();
		        a = getCountryFromJSF(context);
		        System.out.println(a);
		        return Long.parseLong(a);
		        
		    }
	public ImageSujet getImageSujet(){
		ImageSujet img=imagesujetservice.findImageSujet(outcome());
		return img;
	}
	public ImageSujet getImageSujet(Long idsujet){
		ImageSujet img=imagesujetservice.findImageSujet(idsujet);
		return img;
	}
}

package tn.esprit.spring.Controller.GestionUser;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.Model.ImageUser;
import tn.esprit.spring.Service.GestionUser.IImageUserService;


@Controller(value = "ImageUserController")
@ELBeanName(value = "ImageUserController")
public class ImageUserController {
	
	@Autowired
	IImageUserService imageUserService;
	
	private Long id;
	private String image;
	
	public ImageUser getImageUser(Long idUser){
		ImageUser img=imageUserService.findImageUser(idUser);
		System.out.println(img);
		return img;
	}

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
	
	

}

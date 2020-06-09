package tn.esprit.spring.Controller.Forum;

import java.util.List;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.Model.User;
import tn.esprit.spring.Model.Forum.Commentaire;
import tn.esprit.spring.Service.Forum.ICommentaireService;
import tn.esprit.spring.Service.Forum.ISujetService;


@Controller(value = "singleblogController")
@ELBeanName(value = "singleblogController")
@Join(path = "/single-blog", to = "/fourm/single-blog.jsf")
public class ISingleBlogControllerImpl{
	@Autowired
	ISujetService iSujetService;
	
	@Autowired ICommentaireService icommentaireService;
	private Long id;
	private String description;
	private Commentaire commentaire;
	private User idUser;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Commentaire getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(Commentaire commentaire) {
		this.commentaire = commentaire;
	}

	public User getIdUser() {
		return idUser;
	}

	public void setIdUser(User idUser) {
		this.idUser = idUser;
	}

	public  List<Commentaire> afficherCommentaireOfSujet(Long sujetId) {
	
		return icommentaireService.getCommentaireOfSujet(sujetId);
		}
	
	public String ajoutercommentaire(Long sujetId, Long userId){
		if (userId==null)
		{
			return "/login";
		}
		String navigateTo =null;
		Commentaire com2= new Commentaire ();
		com2.setDescription(description);
		System.out.println("********"+description);
		icommentaireService.ajouterCommentaire(com2, sujetId, userId);
		   setDescription(null);
		return navigateTo ;
	}
	
	public int countNbcommentaire(Long sujetId){
		return icommentaireService.countNbcommentaire(sujetId);
	}
	
	public String removeCommentarie(Long comId,Long userId){
	    String navigateTo="null";
		icommentaireService.RemoveCommentairebyIdUser(comId, userId);
		return navigateTo;
	}
	
    public int verificationCombyId(Long comId,Long userId){
    	if (userId==null)
    		userId=(long) 0;
    	
    	return icommentaireService.verivcationCom(comId, userId);
    }

}

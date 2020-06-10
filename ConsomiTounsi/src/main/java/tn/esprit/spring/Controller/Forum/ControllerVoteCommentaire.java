package tn.esprit.spring.Controller.Forum;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.Model.Forum.Vote;
import tn.esprit.spring.Service.Forum.IVoteCommentaire;

@Controller(value = "ControllerVoteCom")
@ELBeanName(value = "ControllerVotesCom")
@Transactional
public class ControllerVoteCommentaire {

	@Autowired
	IVoteCommentaire iVoteCommentaire;
	Vote v=new Vote();

	public String ajouterVote(Long userId, Long comId) {
		if (userId==null)
			userId=(long) 0;
		
		String navigateTo = null;
		if (iVoteCommentaire.verificationvoteChoix(userId, comId) == 1) {
			iVoteCommentaire.deletevoteById(comId, userId);
			iVoteCommentaire.countlikeCom(comId);
			iVoteCommentaire.countdislikCom(comId);
		}
		else if (iVoteCommentaire.verificationvoteChoix(userId, comId) == 2) {
			iVoteCommentaire.Updatelike(comId, userId);
			iVoteCommentaire.countlikeCom(comId);
			iVoteCommentaire.countdislikCom(comId);
		} else
			iVoteCommentaire.ajouterlike(v, comId, userId);
		iVoteCommentaire.countlikeCom(comId);
		iVoteCommentaire.countdislikCom(comId);
		return navigateTo;
	}

	public String ajouterVotedislike(Long userId, Long comId) {
		if (userId==null)
			userId=(long) 0;
		String navigateTo = null;
		if (iVoteCommentaire.verificationvoteChoix(userId, comId) == 2) {
			iVoteCommentaire.deletevoteById(comId, userId);
			iVoteCommentaire.countlikeCom(comId);
			iVoteCommentaire.countdislikCom(comId);
		}

		else if (iVoteCommentaire.verificationvoteChoix(userId, comId) == 1) {
			iVoteCommentaire.Updatedislike(comId, userId);
			iVoteCommentaire.countlikeCom(comId);
			iVoteCommentaire.countdislikCom(comId);
		} else
			iVoteCommentaire.ajouterdislike(v, comId, userId);
		iVoteCommentaire.countlikeCom(comId);
		iVoteCommentaire.countdislikCom(comId);
		return navigateTo;
	}

	public int verfication(Long userId, Long comId) {
		if (userId==null)
			userId=(long) 0;
		return iVoteCommentaire.verificationvoteChoix(userId, comId);
	}
}

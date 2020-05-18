package tn.esprit.spring.Controller.Forum;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.Model.Forum.VoteSujet;
import tn.esprit.spring.Service.Forum.IVoteSujetService;

@Controller(value = "ControllerVoteSujet")
@ELBeanName(value = "ControllerVotesSujet")
@Transactional
public class ControllerVoteSujet {

	@Autowired
	IVoteSujetService ivoteSujetService;
	VoteSujet v = new VoteSujet();

	public String ajouterVote(Long userId, Long sujetId) {
		String navigateTo = null;
		if (ivoteSujetService.verificationvoteChoix(userId, sujetId) == 1) {
			ivoteSujetService.deletevoteById(sujetId, userId);
			ivoteSujetService.countlike(sujetId);
			ivoteSujetService.countdislik(sujetId);
		}
		else if (ivoteSujetService.verificationvoteChoix(userId, sujetId) == 2) {
			ivoteSujetService.Updatelike(sujetId, userId);
			ivoteSujetService.countlike(sujetId);
			ivoteSujetService.countdislik(sujetId);
		} else
			ivoteSujetService.ajouterlike(v, sujetId, userId);
		ivoteSujetService.countlike(sujetId);
		ivoteSujetService.countdislik(sujetId);
		return navigateTo;
	}

	public String ajouterVotedislike(Long userId, Long sujetId) {
		String navigateTo = null;
		if (ivoteSujetService.verificationvoteChoix(userId, sujetId) == 2) {
			ivoteSujetService.deletevoteById(sujetId, userId);
			ivoteSujetService.countlike(sujetId);
			ivoteSujetService.countdislik(sujetId);
		}

		else if (ivoteSujetService.verificationvoteChoix(userId, sujetId) == 1) {
			ivoteSujetService.Updatedislike(sujetId, userId);
			ivoteSujetService.countlike(sujetId);
			ivoteSujetService.countdislik(sujetId);
		} else
			ivoteSujetService.ajouterdislike(v, sujetId, userId);
		ivoteSujetService.countlike(sujetId);
		ivoteSujetService.countdislik(sujetId);
		return navigateTo;
	}

	public int verfication(Long userId, Long sujetId) {
		return ivoteSujetService.verificationvoteChoix(userId, sujetId);
	}
}

package tn.esprit.spring.Service.Forum;

import java.util.List;

import tn.esprit.spring.Model.Forum.*;



public interface ICommentaireService {

	public int ajouterCommentaire(Commentaire v,Long sujetId,Long userId);
	public List<Commentaire> getCommentaire(Long sujetId,Long userId);
	public Commentaire getCommentairebyId(Long comId);
	public List<Commentaire> getCommentaireOfSujet(Long sujetId);
	public int  deletecommentairevoteById(Long  comId,Long userId,Long sujetId);
	public void modifierCommentaire(String desc,Long comId);
    public String findNamebyCommentaire(Long comId);
    public int countNbcommentaire(Long id);
}

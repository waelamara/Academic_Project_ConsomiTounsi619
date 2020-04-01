package tn.esprit.spring.DAO.Charite;

import java.util.List;

import tn.esprit.spring.Model.Charite.Charite;

public interface ChariteDAO {
	boolean saveCharit(Charite Charite);
	List<Charite> getAllChariteList();
	public int saveCharite(Long idevents,Long iduser,Charite Charite);
	Charite findOne(Long id);
	public int saveCharite1(Long idevents,Long iduser,Charite Charite);


	
}

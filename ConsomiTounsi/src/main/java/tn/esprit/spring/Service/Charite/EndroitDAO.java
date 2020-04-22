package tn.esprit.spring.Service.Charite;

import java.util.List;

import tn.esprit.spring.Model.Charite.Endroit;

public interface EndroitDAO {
	List<Endroit> getAllEndroitList();
	public int saveEndroit(Long ideventss,Endroit Endroit);
	Endroit findOne(Long id);
	Endroit saveEndroit1(Endroit Endroit);
	
}

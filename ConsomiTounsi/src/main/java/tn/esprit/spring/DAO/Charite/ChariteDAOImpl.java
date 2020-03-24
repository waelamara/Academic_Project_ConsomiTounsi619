package tn.esprit.spring.DAO.Charite;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.Model.Charite.Charite;
import tn.esprit.spring.Repository.Charite.ChariteRepository;

@Service("ChariteDAO")
public class ChariteDAOImpl implements ChariteDAO {
	@Autowired
	private ChariteRepository chariteRepository;

	@Override
	public boolean saveCharit(Charite Charite) {
		// TODO Auto-generated method stub
		chariteRepository.save(Charite);
		return true;
	}

	@Override
	public List<Charite> getAllChariteList() {
		return chariteRepository.findAll();

	}

	@Override
	public Charite saveCharite(Charite Charite) {
		return chariteRepository.save(Charite);

	}

}

package tn.esprit.spring.Service.GestionUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.Model.ImageUser;
import tn.esprit.spring.Model.User;
import tn.esprit.spring.Repository.ImageUserRepository;
@Service
public class ImageUserService implements IImageUserService{
	
	@Autowired
	ImageUserRepository imageUserRepository;

	@Override
	public ImageUser ajouterImage(ImageUser image) {
		return imageUserRepository.save(image);
	}
	@Override
	public ImageUser findOne(long id){
		return imageUserRepository.findById(id).get();
		}

	@Override
	public ImageUser findImageUser(Long idUser) {
		return imageUserRepository.findImageUser(idUser);
		
	}

}

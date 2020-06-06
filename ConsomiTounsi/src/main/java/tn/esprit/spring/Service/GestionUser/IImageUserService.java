package tn.esprit.spring.Service.GestionUser;

import tn.esprit.spring.Model.ImageUser;

public interface IImageUserService {
	
	public ImageUser ajouterImage(ImageUser image);
	public ImageUser findImageUser(Long idUser);
	public ImageUser findOne(long id);

}

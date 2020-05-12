package tn.esprit.spring.Service.Forum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.Model.Forum.ImageSujet;
import tn.esprit.spring.Repository.Forum.ImageSujetRepository;
@Service
public class ImageSujetServiceImpl implements IImageSujetService {
	@Autowired
	ImageSujetRepository imageSujetRepository;

	@Override
	public ImageSujet ajouterImage(ImageSujet image) {
		return imageSujetRepository.save(image);
		
		}

	@Override
	public ImageSujet findImageSujet(Long idSujet) {
		return imageSujetRepository.findImageSujet(idSujet);
	}
	
	}



package tn.esprit.spring.Model.Forum;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@JsonIgnoreProperties
public class ImageSujet implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	private String Image;
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "idSujet", referencedColumnName = "id")
	private Sujet sujetId;
	
	public ImageSujet(){
	super();
	}
		
	public ImageSujet(Long id, String image, Sujet sujetId) {
		super();
		Id = id;
		Image = image;
		this.sujetId = sujetId;
	}
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getImage() {
		return Image;
	}
	public void setImage(String image) {
		Image = image;
	}
	public Sujet getSujetId() {
		return sujetId;
	}
	public void setSujetId(Sujet sujetId) {
		this.sujetId = sujetId;
	}

}

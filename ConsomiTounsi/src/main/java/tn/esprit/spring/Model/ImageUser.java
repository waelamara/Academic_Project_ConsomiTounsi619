package tn.esprit.spring.Model;

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
public class ImageUser implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	private String Image;
	@ManyToOne
	@JsonIgnore
	private User userId;
	public ImageUser() {
		super();
	}
	public ImageUser(Long id, String image, User userId) {
		super();
		Id = id;
		Image = image;
		this.userId = userId;
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
	public User getUserId() {
		return userId;
	}
	public void setUserId(User userId) {
		this.userId = userId;
	}
	
	

}

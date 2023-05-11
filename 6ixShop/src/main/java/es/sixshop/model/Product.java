package es.sixshop.model;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="idProduct")
	private Long idProduct = null;
	
	@ManyToOne
	private User user;
	
	//@Column(name="productName")
	private String productName;
	
	//@Column(name="description")
	private String description;
	
	//@Column(name="category")
	private String category;
	
	//@Column(name="price")
	private int price;
	
	//@Column(name="rating")
	private int rating;
	
	@Lob
	@JsonIgnore
	private Blob imageFile;
	private boolean image;
	
	
	//Constructor necesario para la carga desde BBDD
	protected Product() {}
	
	public Product(String productName, String description, String category, int price, User user) {
		super();
		this.productName = productName;
		this.description = description;
		this.category = category;
		this.price = price;
		this.user = user;
		this.rating = -1;
	}
	
	public Product(String productName, String description, String category, int price) {
		super();
		this.productName = productName;
		this.description = description;
		this.category = category;
		this.price = price;
		this.rating = -1;
	}

	public Long getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(Long idProduct) {
		this.idProduct = idProduct;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Blob getImageFile() {
		return imageFile;
	}

	public void setImageFile(Blob imageFile) {
		this.imageFile = imageFile;
	}

	public boolean isImage() {
		return image;
	}

	public void setImage(boolean image) {
		this.image = image;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
}

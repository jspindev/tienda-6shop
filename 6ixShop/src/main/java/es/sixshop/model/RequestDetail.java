package es.sixshop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="RequestDetail")
public class RequestDetail {
	
	@Column(name="idRequestDetail")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idRequestDetail = null;
	
	@ManyToOne
	private Request request;
	
	@OneToOne
	private Product product;
	
	//@Column(name="ProductPrice")
	private int productPrice;
	
	//@Column(name="rating")
	private int rating;
	
	//Constructor necesario para la carga desde BBDD
	protected RequestDetail() {}
	
	public RequestDetail(Request request, Product product, int productPrice){
		super();
		this.request = request;
		this.product = product;
		this.productPrice = productPrice;
	}

	public Long getIdRequestDetail() {
		return idRequestDetail;
	}

	public void setIdRequestDetail(Long idRequestDetail) {
		this.idRequestDetail = idRequestDetail;
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
}

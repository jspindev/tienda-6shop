package es.sixshop.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Request")
public class Request{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	//@Column(name = "idRequest")
	private Long idRequest = null;
	
	//@Column(name = "lRequestDetail")
	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=true)
	private List<RequestDetail> lRequestDetail;
	
	@ManyToOne
	private User buyerUser;
	
	//@Column(name ="date")
	private LocalDate date;
	
	//@Column(name = "status")
	private String status;
	
	@JsonIgnore
	private int totalPrice;
	
	//Constructor necesario para la carga desde BBDD
	protected Request() {}

	public Request(User buyerUser) {
		super();
		this.buyerUser = buyerUser;
		this.status = "Cart";
		this.date = LocalDate.now();
		this.lRequestDetail = new ArrayList<RequestDetail>();
	}
	
	public Request(User buyerUser, String status) { //PAID
		super();
		this.buyerUser = buyerUser;
		this.status = status;
		this.date = LocalDate.now();
		this.lRequestDetail = new ArrayList<RequestDetail>();
	}

	public Long getIdRequest() {
		return idRequest;
	}

	public void setIdRequest(Long idRequest) {
		this.idRequest = idRequest;
	}

	public List<RequestDetail> getlRequestDetail() {
		return lRequestDetail;
	}

	public void setlRequestDetail(List<RequestDetail> lRequestDetail) {
		this.lRequestDetail = lRequestDetail;
	}

	public User getBuyerUser() {
		return buyerUser;
	}

	public void setBuyerUser(User buyerUser) {
		this.buyerUser = buyerUser;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	////////////////////////////////////////////
	public void setRequestDetail(RequestDetail requestDetail) {
		lRequestDetail.add(requestDetail);
	}
}

package es.sixshop.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.sixshop.model.Product;
import es.sixshop.model.User;
import es.sixshop.repository.ProductRepository;

@Service
public class ProductService {
	private int SIZE_PAGE = 9;
	
	@Autowired
	private ProductRepository productR;
	
	public Optional<Product> findById(long id){
		return productR.findById(id);
	}
	
	public Collection<Product> findAll(){
		return productR.findAll();
	}
	
	public void save(Product product) {
		productR.save(product);
	}
	
	public void update(Product product) {
		productR.save(product);
	}
	
	public void delete(long id) {
		productR.deleteById(id);
	}
	
	public Collection<Product> findBycategory(String category){
		return productR.findBycategory(category);
	}
	
	public Collection<Product> findByUser(User user){
		return productR.findByUser(user);
	}
	
	public Page<Product> findAll(Pageable pageable) {
		return productR.findAll(PageRequest.of(pageable.getPageNumber(),SIZE_PAGE));
	} 
	
	public Page<Product> findByRating(Pageable pageable){
		return productR.findByRating(PageRequest.of(pageable.getPageNumber(),SIZE_PAGE));
		//return null;
	}
	
	public Product findByIdProduct(Long idProduct){
		return productR.findByIdProduct(idProduct);
	}
	
	public Collection<Product> findByMonthSales(String month1, String month2, User user) {
		return productR.findByMonthSales(month1, month2,user);
	}
}

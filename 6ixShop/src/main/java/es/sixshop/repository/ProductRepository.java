package es.sixshop.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.sixshop.model.Product;
import es.sixshop.model.User;

public interface ProductRepository extends JpaRepository<Product, Long>{
	Collection<Product> findByUser(User user);
	Collection<Product> findBycategory(String category);
	Page<Product> findAll(Pageable pageable);
	Product findByIdProduct(Long idProduct);
	
	@Query("SELECT PR FROM Product PR WHERE PR.rating>=0 ORDER BY PR.rating DESC")
	Page<Product> findByRating(Pageable pageable);
	
	//@Query("SELECT idProduct, idUser, idRequest , date FROM Product PR, User US, Request REQ, RequestDetail REQDET WHERE PR.user_id_user=US.idUser AND PR.idProduct=REQDET.product_id_product AND REQDET.request_id_request=REQ.id_request  AND US.id_user=5 AND REQ.status='PAID' AND  REQ.date between '20210101' AND '20211231'")
	@Query("SELECT COUNT(*) FROM Product PR, User US, Request REQ, RequestDetail REQDET WHERE PR.user=US.idUser AND PR.idProduct=REQDET.product AND REQDET.request=REQ.idRequest  AND US.idUser=?3 AND REQ.status='PAID' AND  REQ.date between '?1' AND '?2'")
    Collection<Product> findByMonthSales(String month1, String month2,User user);
}

package es.sixshop.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import es.sixshop.service.ProductService;
import es.sixshop.service.RequestService;
import es.sixshop.model.Product;
import es.sixshop.model.Request;
import es.sixshop.model.RequestDetail;
import es.sixshop.model.User;
import es.sixshop.repository.ProductRepository;
import es.sixshop.repository.UserRepository;

@Controller
public class ProfileController {	
	@Autowired
	private UserRepository userR;
	
	@Autowired
	private ProductService productS;
	
	@Autowired
	private RequestService requestS;
	
	@GetMapping("/profile")
	public String showProfile(Model model, HttpServletRequest request) {
		int totalPrice = 0;
		String month1, month2;
		int psales = 0;
		
		//Datos usuario
		String nickname = request.getUserPrincipal().getName();
		User user = userR.findByNickname(nickname).orElseThrow();
		
		model.addAttribute("user",user);
		model.addAttribute("nickname",user.getNickname());
		model.addAttribute("mail",user.getMail());
		model.addAttribute("profile",true);
		
		
		//Datos productos subidos
		Collection<Product> products = productS.findByUser(user);
		model.addAttribute("products",products);
		List<Integer> lSales = new ArrayList<Integer>();
		//Datos productos vendidos
		for(int x='0';x<12;x++) {
			month1 = "2021"+x+"01";
			month2 = "2021"+x+"31";
			Collection<Product> productSales = productS.findByMonthSales(month1, month2, user);
			psales=0;
			for(Product objProduct : productSales) {
				psales++;
			}
			lSales.add(psales);
		}
		
		model.addAttribute("nSales",lSales);
		
		/*List<Integer> lSales = new ArrayList<Integer>();
		for(int x=0;x<12;x++) {
			month1 = "2021"+x+"01";
			month2 = "2021"+x+"31";
			lSales.add(productS.findByMonthSales(month1, month2, user));
		}
		model.addAttribute("nSales",lSales);*/
		
		//Datos pedidos comprados
		Collection<Request> requests = requestS.findByBuyerUserAndStatusPaid(user);
		for (Request objRequest : requests) { //Se obtiene la suma total de cada pedido
			totalPrice = 0;
			for (RequestDetail objRequestDetail : objRequest.getlRequestDetail()) {
				totalPrice+=objRequestDetail.getProductPrice();
			}
			objRequest.setTotalPrice(totalPrice);
		}
		model.addAttribute("requests",requests);
		
		return "profile";
	}
	
	@PostMapping("/profile")
    public String newProduct(HttpServletRequest request, Model model, Product product, MultipartFile imageField) throws IOException {
		String nickname = request.getUserPrincipal().getName();
        User user = userR.findByNickname(nickname).orElseThrow();
    	
		if (!imageField.isEmpty()) {
			product.setImageFile(BlobProxy.generateProxy(imageField.getInputStream(), imageField.getSize()));
			product.setImage(true);
		}

		//product.setIdUser(user.getIdUser());
		product.setUser(user);
		productS.save(product);
		
		model.addAttribute("product", product.getIdProduct());
		
		return "redirect:/profile";
    }
}

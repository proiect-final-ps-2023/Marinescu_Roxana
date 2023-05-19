package com.example.demo;

import com.example.demo.enums.Auth;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	@Bean
	CommandLineRunner init(CategoryRepo categoryRepo, UserRepo userRepo, ProductRepo productRepo, WishlistRepo wishlistRepo, CartRepo cartRepo, CategoryService categoryService,
						   UserService userService, ProductService productService, CartService cartService, WishlistService wishlistService,OrderService orderService,OrderRepo orderRepo,LogRepo logRepo, LogService logService){
		return args -> {

			User user1 = new User();
			Wishlist wishlist = new Wishlist();
			Wishlist wishlist1 = new Wishlist();
			Wishlist wishlist2 = new Wishlist();
			LoginLogout loginLogout = new LoginLogout();
			LoginLogout log1 = new LoginLogout();
			LoginLogout log2 = new LoginLogout();
			Cart cart = new Cart();
			Cart cart1 = new Cart();
			Cart cart2 = new Cart();
			user1.setName("Rox");
			user1.setPassword("1234");
			user1.setAuth(Auth.ADMIN);
			user1.setCart(cart2);
			user1.setWishlist(wishlist2);
			user1.setLoginLogout(log1);
			log1.setUser(user1);
			wishlist2.setUser(user1);
			cart2.setUser(user1);
			userRepo.save(user1);
			user1.createCart(cartRepo,cart2);
			user1.createLog(logRepo,log1);
			user1.createWishlist(wishlistRepo,wishlist2);
			User user = new User();
			Order order = new Order();
			user.setName("Alex");
			user.setMail("alex1@gmail.com");
			user.setPassword("12345");
			user.setAuth(Auth.USER);
			user.setLoginLogout(log2);
			log2.setUser(user);
			User u = new User();
			u.setName("Ion");
			u.setPassword("123");
			u.setAuth(Auth.USER);
			cart1.setOrder(order);
			order.setCart(cart1);
			cart1.setUser(u);
			u.setCart(cart1);
			wishlist1.setUser(u);
			user1.createCart(cartRepo,cart1);
			u.setWishlist(wishlist1);
			u.setLoginLogout(loginLogout);
			loginLogout.setUser(u);
			user1.createUser(userRepo,u);
			order.setCart(cart1);

			Category category = new Category();
			category.setName("Haine");
			Category category1 = new Category();
			category1.setName("Mancare");
			Category category2 = new Category();
			category2.setName("Electrocasnice");
			user1.createCateg(categoryRepo,category1);

			Product product = new Product();
			Product product1 = new Product();
			Product product2 = new Product();
			Product product3 = new Product();
			Product product4 = new Product();
			Product product5 = new Product();
			Product product6 = new Product();
			product1.setName("Geaca");
			product1.setPrice(150.5f);
			product1.setImgUrl("geaca.jpg");
			product1.setCategory(category);
			product.setName("Pantaloni");
			product.setPrice(300.5f);
			product.setImgUrl("pantaloni.jpg");
			product.setCategory(category);
			product2.setName("Bluza");
			product2.setPrice(250.5f);
			product2.setImgUrl("bluza.jpg");
			product2.setCategory(category);
			product3.setName("Sosete");
			product3.setPrice(50.0f);
			product3.setImgUrl("sosete.jpg");
			product3.setCategory(category);
			product4.setName("Aspirator");
			product4.setPrice(500);
			product4.setCategory(category2);
			product4.setImgUrl("aspirator.jpg");
			product5.setName("Mixer");
			product5.setPrice(205);
			product5.setCategory(category2);
			product5.setImgUrl("mixer.jpg");
			product6.setName("Microunde");
			product6.setPrice(450);
			product6.setCategory(category2);
			product6.setImgUrl("microunde.jpg");
			user.setWishlist(wishlist);
			user.setCart(cart);

			List<Product> prod = new ArrayList<Product>();
			prod.add(product1);
			prod.add(product);
			prod.add(product2);
			prod.add(product3);
			List<Product> prod1 = new ArrayList<Product>();
			prod1.add(product4);
			prod1.add(product5);
			prod1.add(product6);

			category.setProducts(prod);
			category2.setProducts(prod1);
			user1.createCateg(categoryRepo,category);
			user1.createCateg(categoryRepo,category2);

			wishlist.setUser(user);
			cart.setUser(user);
			user1.createUser(userRepo,user);

			user1.createWishlist(wishlistRepo,wishlist);

			user1.createCart(cartRepo,cart);

			user1.createProd(productRepo,product1);
			user1.createProd(productRepo,product);
			user1.createProd(productRepo,product2);
			user1.createProd(productRepo,product3);
			user1.createProd(productRepo,product4);
			user1.createProd(productRepo,product5);
			user1.createProd(productRepo,product6);

		};

	}

}

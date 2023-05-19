package com.example.demo.controller;
import com.example.demo.DTO.CartDTO;
import com.example.demo.DTO.ProductDTO;
import com.example.demo.DTO.UserDTO;
import com.example.demo.DTO.WishlistDTO;
import com.example.demo.enums.Auth;
import com.example.demo.mapper.CartMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.mapper.WishlistMapper;
import com.example.demo.model.Cart;
import com.example.demo.model.Category;
import com.example.demo.model.User;
import com.example.demo.model.Wishlist;
import com.example.demo.service.CartService;
import com.example.demo.service.UserService;
import com.example.demo.service.WishlistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;
    private UserMapper userMapper;
    private WishlistService wishlistService;
    private WishlistMapper wishlistMapper;
    private CartMapper cartMapper;
    private CartService cartService;

    @GetMapping()
    public List<UserDTO> findAllProd() {
        return userService.findAll();
    }

    public UserController(UserService userService,UserMapper userMapper, WishlistService wishlistService, WishlistMapper wishlistMapper,CartMapper cartMapper,CartService cartService) {
        this.userService = userService;
        this.userMapper=userMapper;
        this.wishlistService=wishlistService;
        this.wishlistMapper=wishlistMapper;
        this.cartMapper=cartMapper;
        this.cartService=cartService;
    }

    @RequestMapping(value="/delete")
    public User delete(@RequestBody User user) {
        System.out.println(user);
        return userService.deleteUser(user);
    }

    public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    @RequestMapping(value="/adduser")
    public User addUser(@RequestBody User user) {
        Wishlist wishlist = new Wishlist();
        Cart cart = new Cart();
        cart.setUser(user);
        wishlist.setUser(user);
        user.setCart(cart);
        user.setWishlist(wishlist);
        if(user.getName().equals("")){
            System.out.println("Nume invalid pentru user");
            return null;}
        if(user.getPassword().equals("")){
            System.out.println("Nu ai introdus o parola!");
            return null;}
        if(user.getAuth()==null){
            System.out.println("Nu ai introdus un rol!");
            return null;}
        if(!isValidEmailAddress(user.getMail())){
            System.out.println("Email invalid");
            return null;
        }
        return userService.createUser(user);
    }

    @RequestMapping(value="/create",method= RequestMethod.POST)
    public void create(@RequestParam User user)
    {
        userService.createUser(user);
    }

    @RequestMapping(value="/update")
    public User update(@RequestBody User user)
    {
        User u = userMapper.mapDtoToModel(userService.findById(user.getId()));
        WishlistDTO wishlistDTO = wishlistService.findByUserId(u.getId());
        wishlistDTO.setUser_id(u.getId());
        Wishlist wishlist = wishlistMapper.mapDtoToModel(wishlistDTO);
        wishlist.setUser(u);

        CartDTO cartDTO = cartService.findByUserId(u.getId());
        cartDTO.setUser_id(u.getId());
        Cart cart = cartMapper.mapDtoToModel(cartDTO);
        cart.setUser(u);

        System.out.println(wishlistDTO.getUser_id());
        if(user.getCart()==null) user.setCart(cart);
        if(user.getWishlist()==null) user.setWishlist(wishlist);
        if(user.getName()==null) user.setName(u.getName());
        if(user.getMail()==null) user.setMail(u.getMail());
        if(user.getPassword()==null) user.setPassword(u.getPassword());
        if(user.getAuth()==null) user.setAuth(u.getAuth());

        return userService.updateUser(user);
    }

    @RequestMapping(value="/all",method =RequestMethod.GET)
    public List<UserDTO> findAll()
    {
        return userService.findAll();
    }

    @RequestMapping(value="/findbyid",method =RequestMethod.GET)
    public UserDTO findById(@RequestParam int id)
    {
        return userService.findById(id);
    }

    @RequestMapping(value = "/login")
    public UserDTO login(@RequestBody User user) {
        if(user.getPassword().equals("")){
            System.out.println("Parola invalida");
            return null;
        }
        if(user.getName().equals("")){
            System.out.println("Username invalid");
            return null;
        }

        UserDTO user1 = userService.login(user.getName(), user.getPassword());
        if(user1!=null)
        return user1;
        else return null;
    }

    @RequestMapping(value = "/getId")
    public UserDTO getId(@RequestBody User user) {
        UserDTO user1 = userService.login(user.getName(), user.getPassword());
        if(user1!=null)
            return user1;
        else return null;
    }
}

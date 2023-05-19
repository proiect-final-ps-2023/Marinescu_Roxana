package com.example.demo.mapper;

import com.example.demo.DTO.UserDTO;
import com.example.demo.model.Cart;
import com.example.demo.model.LoginLogout;
import com.example.demo.model.User;
import com.example.demo.model.Wishlist;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public static UserDTO mapModelToDto(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setAuth(user.getAuth());
        userDTO.setMail(user.getMail());
        userDTO.setPassword(user.getPassword());
        userDTO.setCart_id(user.getCart().getId());
        userDTO.setWishlist_id(user.getWishlist().getId());
        userDTO.setLogin_logout_id(user.getLoginLogout().getId());

        return userDTO;
    }

    public static User mapDtoToModel(UserDTO userDTO){
        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setAuth(userDTO.getAuth());
        user.setMail(userDTO.getMail());
        user.setPassword(userDTO.getPassword());

        Cart cart = new Cart();
        cart.setId(userDTO.getCart_id());
        user.setCart(cart);

        Wishlist wishlist = new Wishlist();
        wishlist.setId(userDTO.getWishlist_id());
        user.setWishlist(wishlist);

        LoginLogout loginLogout = new LoginLogout();
        loginLogout.setId(userDTO.getLogin_logout_id());
        user.setLoginLogout(loginLogout);

        return user;
    }
}

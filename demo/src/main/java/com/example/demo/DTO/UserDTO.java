package com.example.demo.DTO;

import com.example.demo.enums.Auth;

import com.example.demo.model.Cart;
import com.example.demo.model.Wishlist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private int id;
    private String name;
    private String mail;
    private String password;
    private Auth auth;
    private int cart_id;
    private int wishlist_id;
    private int login_logout_id;

}

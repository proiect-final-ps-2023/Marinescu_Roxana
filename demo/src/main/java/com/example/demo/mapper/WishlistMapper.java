package com.example.demo.mapper;

import com.example.demo.DTO.WishlistDTO;
import com.example.demo.model.User;
import com.example.demo.model.Wishlist;
import org.springframework.stereotype.Component;

@Component
public class WishlistMapper {
    public static WishlistDTO mapModelToDto(Wishlist wishlist){
        WishlistDTO wishlistDTO = new WishlistDTO();
        wishlistDTO.setId(wishlist.getId());
        wishlistDTO.setUser_id(wishlist.getUser().getId());
        return wishlistDTO;
    }

    public static Wishlist mapDtoToModel(WishlistDTO wishlistDTO) {
        Wishlist wishlist = new Wishlist();
        wishlist.setId(wishlistDTO.getId());
        User user = new User();
        user.setId(wishlistDTO.getUser_id());
        wishlist.setUser(user);
        return wishlist;
    }
}

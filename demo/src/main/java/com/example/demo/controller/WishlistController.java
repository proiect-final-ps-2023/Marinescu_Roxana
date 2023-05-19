package com.example.demo.controller;

import com.example.demo.DTO.*;
import com.example.demo.mapper.*;
import com.example.demo.model.*;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {
    @Autowired
    private WishlistService wishlistService;
    private UserService userService;
    private ProductService productService;
    private UserMapper userMapper;
    private WishlistMapper wishlistMapper;
    private ProductMapper productMapper;
    private CategoryService categoryService;
    private CategoryMapper categoryMapper;
    private CartService cartService;
    private CartMapper cartMapper;

    public WishlistController(WishlistService wishlistService, UserService userService, ProductService productService, UserMapper userMapper, WishlistMapper wishlistMapper, ProductMapper productMapper, CategoryService categoryService, CategoryMapper categoryMapper,
                              CartService cartService,CartMapper cartMapper) {
        this.wishlistService = wishlistService;
        this.userService = userService;
        this.productService = productService;
        this.userMapper = userMapper;
        this.wishlistMapper = wishlistMapper;
        this.productMapper = productMapper;
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
        this.cartMapper=cartMapper;
        this.cartService=cartService;
    }

    @RequestMapping(value="/add")
    public void add(@RequestBody String s)
    {
        String x = String.valueOf(s.charAt(1));
        String y = String.valueOf(s.charAt(3));
        String z = String.valueOf(s.charAt(5));
        int p = Integer.parseInt(x);
        int u = Integer.parseInt(y);
        int c = Integer.parseInt(z);

        UserDTO userDTO = userService.findById(u);
        ProductDTO productDTO = productService.findByIdDTO(p);
        WishlistDTO wishlistDTO = wishlistService.findByUserId(u);
        CategoryDTO categoryDTO = categoryService.findByIdDTO(c);

        List<ProductDTO> productDTOS = productService.findAllByWishlistId(wishlistDTO.getId());
        productDTOS.add(productDTO);

        List<WishlistDTO> wishlistDTOS = wishlistService.findAllByProductId(productDTO.getId());
        wishlistDTOS.add(wishlistDTO);

        System.out.println(userDTO + "\n" + productDTO + "\n" + wishlistDTO + "\n" + categoryDTO); //DTOs

        System.out.println("\n\n" + wishlistDTOS);//carts

        System.out.println("\n\n" + productDTOS);//products


        Wishlist wishlist = wishlistMapper.mapDtoToModel(wishlistDTO);
        Product product = productMapper.mapDtoToModel(productDTO);
        product.getCategory().setName(categoryDTO.getName());

        User user = userMapper.mapDtoToModel(userDTO);

        ArrayList<Product> ps = new ArrayList<Product>();
        ps.add(product);

        wishlist.setProduct(ps);
        wishlist.setUser(user);

        ArrayList<Wishlist> cs = new ArrayList<Wishlist>();
        cs.add(wishlist);
        product.setWishlist(cs);

        wishlistService.updateWishlist(wishlist);
    }

    @RequestMapping(value="/get")
    public List<ProductDTO> get(@RequestBody int id){

        System.out.println(id);
        WishlistDTO x = wishlistService.findByUserId(id);
        System.out.println(x);
        return productService.findAllByWishlistId(x.getId());

    }

    @RequestMapping(value="/delete")
    public List<Product> delete(@RequestBody String senditem){
        System.out.println(senditem);
        String x = String.valueOf(senditem.charAt(1));
        String y = String.valueOf(senditem.charAt(3));
        int p = Integer.parseInt(x);
        int u = Integer.parseInt(y);

        ProductDTO productDTO = productMapper.mapModelToDto(productService.findbyId(p));
        Product product = productMapper.mapDtoToModel(productDTO);
        List<WishlistDTO> wishlistDTOS = wishlistService.findAllByProductId(productDTO.getId());
        List<CartDTO> cartDTOS = cartService.findAllByProductId(productDTO.getId());
        Category category = categoryService.findById(productDTO.getCategory_id());

        product.setWishlist(wishlistDTOS.stream().map(WishlistMapper::mapDtoToModel).collect(Collectors.toList()));
        product.setCart(cartDTOS.stream().map(CartMapper::mapDtoToModel).collect(Collectors.toList()));
        product.setCategory(category);

        Wishlist wishlist = wishlistMapper.mapDtoToModel(wishlistService.findByUserId(u));

        for(Wishlist i:product.getWishlist()){
            if(i.getId()==wishlist.getId()) {product.getWishlist().remove(i);
            break;}
        }

        productService.updateProd(product);
        List<Product> productList = productService.findAllByWishlistId(wishlist.getId()).stream().map(ProductMapper::mapDtoToModel).collect(Collectors.toList());
        return productList;
    }

}

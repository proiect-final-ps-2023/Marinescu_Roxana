package com.example.demo.controller;

import com.example.demo.DTO.*;
import com.example.demo.mapper.*;
import com.example.demo.model.*;
import com.example.demo.repository.CartRepo;
import com.example.demo.repository.UserRepo;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;
    private CartRepo cartRepo;
    private UserService userService;
    private ProductService productService;
    private UserMapper userMapper;
    private CartMapper cartMapper;
    private ProductMapper productMapper;
    private CategoryService categoryService;
    private CategoryMapper categoryMapper;
    private WishlistService wishlistService;
    private WishlistMapper wishlistMapper;

    public CartController(CategoryService categoryService,CategoryMapper categoryMapper, CartService cartService,CartMapper cartMapper,CartRepo cartRepo, UserService userService, ProductService productService, UserMapper userMapper, ProductMapper productMapper,
                          WishlistMapper wishlistMapper,WishlistService wishlistService) {
        this.cartService = cartService;
        this.userService = userService;
        this.productService = productService;
        this.userMapper = userMapper;
        this.productMapper = productMapper;
        this.cartRepo = cartRepo;
        this.cartMapper = cartMapper;
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
        this.wishlistMapper=wishlistMapper;
        this.wishlistService=wishlistService;
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
        CartDTO cartDTO = cartService.findByUserId(u);
        CategoryDTO categoryDTO = categoryService.findByIdDTO(c);

        List<ProductDTO> productDTOS = productService.findAllByCartId(cartDTO.getId());
        productDTOS.add(productDTO);

        List<CartDTO> carts = cartService.findAllByProductId(productDTO.getId());
        carts.add(cartDTO);

        Cart cart = cartMapper.mapDtoToModel(cartDTO);
        Product product = productMapper.mapDtoToModel(productDTO);
        product.getCategory().setName(categoryDTO.getName());

        User user = userMapper.mapDtoToModel(userDTO);

        ArrayList<Product> ps = new ArrayList<Product>();
        ps.add(product);

        cart.setProduct(ps);
        cart.setUser(user);

        ArrayList<Cart> cs = new ArrayList<Cart>();
        cs.add(cart);
        product.setCart(cs);

        cartService.updateCart(cart);
    }

    @RequestMapping(value="/get")
    public List<ProductDTO> get(@RequestBody int id){

        CartDTO x = cartService.findByUserId(id);
        return productService.findAllByCartId(x.getId());

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

        Cart cart = cartMapper.mapDtoToModel(cartService.findByUserId(u));

        for(Cart i:product.getCart()){
            if(i.getId()==cart.getId()) {product.getCart().remove(i);
                break;}
        }

        productService.updateProd(product);
        List<Product> productList = productService.findAllByCartId(cart.getId()).stream().map(ProductMapper::mapDtoToModel).collect(Collectors.toList());
        return productList;
    }
}

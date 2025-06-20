package ru.shop.project.diplom.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.shop.project.diplom.dto.BasketDTO;
import ru.shop.project.diplom.dto.PayBasketDTO;
import ru.shop.project.diplom.models.Basket;
import ru.shop.project.diplom.models.DefaultMessageResponse;
import ru.shop.project.diplom.models.Product;
import ru.shop.project.diplom.models.User;
import ru.shop.project.diplom.services.BasketService;
import ru.shop.project.diplom.services.ProductService;
import ru.shop.project.diplom.services.UserService;

@RestController
@RequestMapping("/basket")
public class BasketController {

    BasketService basketService;
    ProductService productService;
    UserService userService;
    ModelMapper modelMapper;

    public BasketController(BasketService basketService, UserService userService, ProductService productService, ModelMapper modelMapper) {
        this.basketService = basketService;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.productService = productService;
    }

    @PostMapping("/add")
    public ResponseEntity<DefaultMessageResponse> addToBasket(@RequestBody BasketDTO basketDTO) {
        String UserLogin = basketDTO.getLogin();
        String ProductName = basketDTO.getName();
        User user = userService.findByLogin(UserLogin);
        Product product = productService.findByName(ProductName);

        if (user != null && product != null) {
            basketService.addProductToBasket(convertToBasket(basketDTO, user, product));
            return new ResponseEntity<>(new DefaultMessageResponse("Product added to basket"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new DefaultMessageResponse("Product or user not found"), HttpStatus.NOT_FOUND);
    }

    @PostMapping("/pay")
    public ResponseEntity<DefaultMessageResponse> payForBasket(@RequestBody PayBasketDTO payBasketDTO) {
        String UserLogin = payBasketDTO.getUserLogin();
        User user = userService.findByLogin(UserLogin);

        if (user != null) {
            Integer totalPrice = basketService.getTotalPrice(user);
            if (totalPrice == null) {
                return new ResponseEntity<>(new DefaultMessageResponse("Your basket is empty"), HttpStatus.BAD_REQUEST);
            }

            int balance = user.getDeposit() - totalPrice;
            if (balance < 0) {
                return new ResponseEntity<>(new DefaultMessageResponse(String.format("There are not enough funds in the balance. Add %d coins", balance)), HttpStatus.BAD_REQUEST);
            } else  {
                user.setDeposit(balance);
//                userService.updateFunds(user);
                basketService.deleteAllByUser(user);
                return new ResponseEntity<>(new DefaultMessageResponse(String.format("You have successfully purchased the product. There are %d coins left in your balance", balance)), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(new DefaultMessageResponse("User not found"), HttpStatus.NOT_FOUND);
    }

    private Basket convertToBasket(BasketDTO basketDTO, User user, Product product) {
        Basket basket = modelMapper.map(basketDTO, Basket.class);
        basket.setUser(user);
        basket.setProduct(product);
        return basket;
    }
}

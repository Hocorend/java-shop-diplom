package ru.shop.project.diplom.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.shop.project.diplom.models.Basket;
import ru.shop.project.diplom.repositories.BasketRepository;

import java.util.Optional;

@Service
@Transactional
public class BasketService {

    private final BasketRepository basketRepository;

    @Autowired
    public BasketService(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }

    @Transactional
    public void addProductToBasket(Basket basket){
        Optional<Basket> existingBasket = basketRepository.findByUserAndProduct(basket.getUser(), basket.getProduct());
        if (existingBasket.isEmpty()) {
            basketRepository.save(basket);
        } else {
            basket.setCount(existingBasket.get().getCount() + basket.getCount());
            basketRepository.updateBasket(basket);
        }
    }
}

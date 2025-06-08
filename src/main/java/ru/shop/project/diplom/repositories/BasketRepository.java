package ru.shop.project.diplom.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.shop.project.diplom.models.Basket;
import ru.shop.project.diplom.models.Product;
import ru.shop.project.diplom.models.User;

import java.util.Optional;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Integer> {
    public Optional<Basket> findByUserAndProduct(User user, Product product);

    @Modifying
    @Transactional
    @Query("UPDATE Basket b SET b.count = :#{#basket.count} WHERE b.id = :#{#basket.id}")
    void updateBasket(Basket basket);
}

package ru.shop.project.diplom.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.shop.project.diplom.models.Basket;
import ru.shop.project.diplom.models.Product;
import ru.shop.project.diplom.models.User;

import java.util.Optional;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Integer> {
    public Optional<Basket> findByUserAndProduct(User user, Product product);

    @Modifying
    @Query("UPDATE Basket b SET b.count = :count WHERE b.id = :id")
    void updateBasketsById(@Param("count") int count, @Param("id") int id);


    @Query("SELECT SUM(b.count * b.product.cost) FROM Basket b WHERE b.user = :user")
    public Integer getTotalPrice(User user);

    @Modifying
    @Query("DELETE FROM Basket b WHERE b.user = :user")
    public void deleteAllByUser(User user);
}

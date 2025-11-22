package ru.shop.project.diplom.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.shop.project.diplom.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    public Product findByName(String name);
}

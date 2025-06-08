package ru.shop.project.diplom.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ProductDTO {

    @Column(name = "name")
    private String name;
    @Column(name = "cost")
    private int cost;
}

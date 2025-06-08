package ru.shop.project.diplom.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.Data;

@Data
public class BasketDTO {

    @JsonProperty("user_login")
    private String login;
    @JsonProperty("product_name")
    private String name;
    @Column(name = "count")
    @JsonProperty("product_count")
    private int productCount;
}

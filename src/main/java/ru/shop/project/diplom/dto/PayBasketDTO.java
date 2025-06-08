package ru.shop.project.diplom.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PayBasketDTO {

    @JsonProperty("user_login")
    String userLogin;
}

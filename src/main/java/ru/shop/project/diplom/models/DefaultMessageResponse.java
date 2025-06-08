package ru.shop.project.diplom.models;

import lombok.Data;

@Data
public class DefaultMessageResponse {
    private String message;

    public DefaultMessageResponse(String message) {
        this.message = message;
    }
}

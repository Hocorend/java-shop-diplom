package ru.shop.project.diplom.models;

import lombok.Data;
import ru.shop.project.diplom.dto.ProductDTO;

import java.util.List;

@Data
public class ProductResponse {

    private List<ProductDTO> products;

    public ProductResponse(List<ProductDTO> products) {
        this.products = products;
    }
}

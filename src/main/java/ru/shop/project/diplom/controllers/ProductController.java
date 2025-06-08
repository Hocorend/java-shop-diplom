package ru.shop.project.diplom.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.shop.project.diplom.dto.ProductDTO;
import ru.shop.project.diplom.models.Product;
import ru.shop.project.diplom.models.ProductResponse;
import ru.shop.project.diplom.services.ProductService;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final ModelMapper modelMapper;

    public ProductController(ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ProductResponse getAllProducts() {
        return new ProductResponse(productService.findAll().stream()
                .map(this::convertToProductDTO)
                .collect(Collectors.toList()));
    }

    private ProductDTO convertToProductDTO(Product product) {
        return modelMapper.map(product, ProductDTO.class);
    }
}

package ru.shop.project.diplom.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class UserDTO {

    @Column(name = "login")
    private String login;
    @Column(name = "deposit")
    private int deposit;}

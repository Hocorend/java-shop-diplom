package ru.shop.project.diplom.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.shop.project.diplom.dto.DepositDTO;
import ru.shop.project.diplom.models.DepositUpdateResponse;
import ru.shop.project.diplom.models.User;
import ru.shop.project.diplom.services.UserService;

@RestController
@RequestMapping("/users/deposit/add")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    private ResponseEntity<DepositUpdateResponse> addDeposit(@RequestBody DepositDTO depositDTO) {
        User user = userService.findByLogin(depositDTO.getLogin());
        userService.addDeposit(user, depositDTO.getAmount());
        DepositUpdateResponse response = new DepositUpdateResponse(depositDTO.getAmount(), user.getDeposit());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

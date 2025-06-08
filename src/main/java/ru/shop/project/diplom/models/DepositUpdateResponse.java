package ru.shop.project.diplom.models;

import lombok.Data;
import ru.shop.project.diplom.util.ResponseMessage;

@Data
public class DepositUpdateResponse {

    private String message;

    public DepositUpdateResponse(int addedDeposit, int deposit) {
        this.message = String.format(ResponseMessage.SuccessUpdateResponse, addedDeposit, deposit);
    }
}

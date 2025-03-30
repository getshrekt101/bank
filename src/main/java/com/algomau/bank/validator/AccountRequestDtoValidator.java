package com.algomau.bank.validator;

import com.algomau.bank.dto.request.AccountRequestDto;
import com.algomau.bank.exception.BadRequestException;

import java.util.ArrayList;
import java.util.List;

public class AccountRequestDtoValidator {

    public static List<String> validate(AccountRequestDto dto) {
        List<String> errors = new ArrayList<>();

        if (dto == null) {
            errors.add("AccountRequestDto cannot be null");
            return errors;
        }

        if (dto.getBalance() == null) {
            errors.add("Balance is required");
        }

        if (dto.getType() == null) {
            errors.add("Account type is required");
        }

        if (dto.getBankId() == null) {
            errors.add("Bank details are required");
        }

        if (dto.getUserId() == null) {
            errors.add("User details are required");
        }
        return errors;
    }

    public static boolean isValid(AccountRequestDto dto) {
        return validate(dto).isEmpty();
    }

    // AccountRequestDtoValidator
    public static void assertValid(AccountRequestDto dto) {
        List<String> errors = validate(dto);
        if (!errors.isEmpty()) {
            throw new BadRequestException("bad-request", errors.stream().findAny().get());
        }
    }
}
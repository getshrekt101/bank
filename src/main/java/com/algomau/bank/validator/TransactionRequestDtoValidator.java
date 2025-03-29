package com.algomau.bank.validator;


import com.algomau.bank.dto.request.TransactionRequestDto;
import com.algomau.bank.exception.BadRequestException;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class TransactionRequestDtoValidator {

    public static List<String> validate(TransactionRequestDto dto) {
        List<String> errors = new ArrayList<>();

        if (dto == null) {
            errors.add("TransactionRequestDto cannot be null");
            return errors;
        }

        // Amount checks
        if (dto.getAmount() == null) {
            errors.add("Transaction amount is required");
        }

        // Item name
        if (isBlank(dto.getItemName())) {
            errors.add("Item name is required");
        }

        // Organization name
        if (dto.getOrganizationName() == null) {
            errors.add("Organization is required");
        }

        // Status
        if (dto.getStatus() == null) {
            errors.add("Transaction status is required");
        }

        // Account
        if (dto.getAccount() == null) {
            errors.add("Associated account is required");
        } else {
            List<String> accountErrors = AccountRequestDtoValidator.validate(dto.getAccount());
            errors.addAll(accountErrors);
        }

        return errors;
    }

    public static boolean isValid(TransactionRequestDto dto) {
        return validate(dto).isEmpty();
    }

    // TransactionRequestDtoValidator
    public static void assertValid(TransactionRequestDto dto) {
        List<String> errors = validate(dto);
        if (!errors.isEmpty()) {
            throw new BadRequestException("bad-request", errors.stream().findAny().get());
        }
    }

}
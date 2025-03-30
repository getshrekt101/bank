package com.algomau.bank.validator;

import com.algomau.bank.dto.request.BankRequestDto;
import com.algomau.bank.exception.BadRequestException;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class BankRequestDtoValidator {

    public static List<String> validate(BankRequestDto dto) {
        List<String> errors = new ArrayList<>();

        if (dto == null) {
            errors.add("BankRequestDto cannot be null");
            return errors;
        }

        if (isBlank(dto.getName())) {
            errors.add("Bank name is required");
        }

        if (isBlank(dto.getCode())) {
            errors.add("Bank code is required");
        }

        if (isBlank(dto.getBranchNumber())) {
            errors.add("Branch number is required");
        }

        if (dto.getType() == null) {
            errors.add("Bank type is required");
        }

        if (dto.getAddress() == null) {
            errors.add("Bank address is required");
        } else {
            List<String> addressErrors = AddressRequestDtoValidator.validate(dto.getAddress());
            if (!addressErrors.isEmpty()) {
                addressErrors.forEach(error -> errors.add("Address: " + error));
            }
        }

        return errors;
    }

    public static boolean isValid(BankRequestDto dto) {
        return validate(dto).isEmpty();
    }

    // BankRequestDtoValidator
    public static void assertValid(BankRequestDto dto) {
        List<String> errors = validate(dto);
        if (!errors.isEmpty()) {
            throw new BadRequestException("bad-request", errors.stream().findAny().get());
        }
    }

}
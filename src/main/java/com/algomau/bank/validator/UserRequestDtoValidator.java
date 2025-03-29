package com.algomau.bank.validator;

import com.algomau.bank.dto.request.UserRequestDto;
import com.algomau.bank.exception.BadRequestException;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.*;

public class UserRequestDtoValidator {

    public static List<String> validate(UserRequestDto dto) {
        List<String> errors = new ArrayList<>();

        if (dto == null) {
            errors.add("UserRequestDto cannot be null");
            return errors;
        }

        if (dto.getAddress() == null) {
            errors.add("Address is required");
        } else {
            List<String> addressErrors = AddressRequestDtoValidator.validate(dto.getAddress());
            errors.addAll(addressErrors);
        }

        if (isBlank(dto.getEmail())) {
            errors.add("Email is required");
        }

        if (isBlank(dto.getSinNumber())) {
            errors.add("SIN number is required");
        }

        if (dto.getBusinessName() != null && isBlank(dto.getBusinessName())) {
            errors.add("Business name is required");
        }

        return errors;
    }

    public static boolean isValid(UserRequestDto dto) {
        return validate(dto).isEmpty();
    }


    // UserRequestDtoValidator
    public static void assertValid(UserRequestDto dto) {
        List<String> errors = validate(dto);
        if (!errors.isEmpty()) {
            throw new BadRequestException("bad-request", errors.stream().findAny().get());
        }
    }
}
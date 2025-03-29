package com.algomau.bank.validator;


import com.algomau.bank.dto.request.UserAccountRequestDto;
import com.algomau.bank.exception.BadRequestException;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class UserAccountRequestDtoValidator {

    public static List<String> validate(UserAccountRequestDto dto) {
        List<String> errors = new ArrayList<>();

        if (dto == null) {
            errors.add("UserAccountRequestDto cannot be null");
            return errors;
        }

        if (dto.getRole() == null) {
            errors.add("User role is required");
        }

        if (isBlank(dto.getUserName())) {
            errors.add("Username is required");
        }

        if (isBlank(dto.getPassword())) {
            errors.add("Password is required");
        }

        if (dto.getUser() == null) {
            errors.add("User details are required");
        } else {
            List<String> userErrors = UserRequestDtoValidator.validate(dto.getUser());
            errors.addAll(userErrors);
        }

        return errors;
    }


    // UserAccountRequestDtoValidator
    public static void assertValid(UserAccountRequestDto dto) {
        List<String> errors = validate(dto);
        if (!errors.isEmpty()) {
            throw new BadRequestException("bad-request", errors.stream().findAny().get());
        }
    }
}
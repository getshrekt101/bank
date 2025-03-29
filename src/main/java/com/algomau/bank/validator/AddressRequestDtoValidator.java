package com.algomau.bank.validator;


import com.algomau.bank.dto.request.AddressRequestDto;
import com.algomau.bank.exception.BadRequestException;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class AddressRequestDtoValidator {

    public static List<String> validate(AddressRequestDto dto) {
        List<String> errors = new ArrayList<>();

        if (dto == null) {
            errors.add("AddressRequestDto cannot be null");
            return errors;
        }

        if (isBlank(dto.getStreet())) {
            errors.add("Street is required");
        }

        if (isBlank(dto.getCity())) {
            errors.add("City is required");
        }

        if (isBlank(dto.getState())) {
            errors.add("State is required");
        }

        if (isBlank(dto.getPostalCode())) {
            errors.add("Postal code is required");
        }

        if (isBlank(dto.getCountry())) {
            errors.add("Country is required");
        }

        if (isBlank(dto.getPhone())) {
            errors.add("Phone is required");
        }

        if (dto.getType() == null) {
            errors.add("Address type is required");
        }

        return errors;
    }

    public static boolean isValid(AddressRequestDto dto) {
        return validate(dto).isEmpty();
    }

    // AddressRequestDtoValidator
    public static void assertValid(AddressRequestDto dto) {
        List<String> errors = validate(dto);
        if (!errors.isEmpty()) {
            throw new BadRequestException("bad-request", errors.stream().findAny().get());
        }
    }

}
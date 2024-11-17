package com.pharmanet.config.validations;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {

    private static final String PHONE_NUMBER_PATTERN = "^3\\d{9}$";

    @Override
    public void initialize(PhoneNumber constraintAnnotation) {
    }

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
        // Permitir el valor vacío como válido
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            return true;
        }
        // Validar que coincida con el patrón si no está vacío
        return phoneNumber.matches(PHONE_NUMBER_PATTERN);
    }
}
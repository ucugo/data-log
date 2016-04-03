package com.mahull.model.model.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by Ugo on 03/04/2016.
 */
public class NonBlankImpl implements ConstraintValidator<NonBlank, String> {

    @Override
    public void initialize(NonBlank constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (value.trim().length() == 0) {
            return false;
        }
        return true;
    }
}

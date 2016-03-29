package com.mahull.model.util;

import com.mahull.model.model.ModelObject;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Ugo on 27/03/2016.
 */
public class TestCase {

    protected void validateConstraint(ModelObject modelObject, int violationSize) {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

        Set<ConstraintViolation<ModelObject>> constraintViolations =
                factory.getValidator().validate(modelObject);

        assertThat(constraintViolations.size()).isEqualTo(violationSize);
    }
}

package com.mahull.model.model.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Ugo on 03/04/2016.
 */

@Retention(RUNTIME)
@Target(FIELD)
@Constraint(validatedBy = NonBlankImpl.class)
@Documented
/**
 * Blank field checker.
 */
public @interface NonBlank {

    /**
     *
     * @return message;
     */
    String message() default "Value cannot be blank";

    /**
     *
     * @return class.
     */
    Class<?>[] groups() default {};

    /**
     *
     * @return class array.
     */
    Class<? extends Payload>[] payload() default {};
}

package com.maat.bestbuy.integration.validation.annotation;

import com.maat.bestbuy.integration.model.Payload;

import javax.validation.Constraint;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DropdownConstraintValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface DropdownValid  {
    String acceptedValues();

    String message() default "StringValid";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}

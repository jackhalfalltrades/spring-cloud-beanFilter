package com.spring.beanFilter.validation.annotation;

import com.spring.beanFilter.configuration.PropertyManager;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class DropdownConstraintValidator implements ConstraintValidator<DropdownValid, String> {


    private List<String> valueList;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null)
            return false;
        else return valueList.contains(value.toLowerCase());
    }

    @Override
    public void initialize(DropdownValid constraintAnnotation) {
        valueList = PropertyManager.getStringListProperty(constraintAnnotation.acceptedValues(), ",");
    }
}

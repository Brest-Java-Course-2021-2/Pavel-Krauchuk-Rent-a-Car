package com.epam.brest.web_app.validators;

import com.epam.brest.model.Car;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import static com.epam.brest.model.constants.CarConstants.CAR_MODEL_SIZE;

public class CarValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Car.class.equals(clazz);
    }
    @Override
    public void validate(Object target, Errors errors) {

        ValidationUtils.rejectIfEmpty(errors, "moel", "model.empty");
        Car car = (Car) target;

        if (StringUtils.hasLength(car.getModel())
                && car.getModel().length() > CAR_MODEL_SIZE) {
            errors.rejectValue("model", "model.maxSize");
        }
    }

}

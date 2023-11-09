package umc.spring.validation.annotation;


import java.lang.annotation.*;

import javax.validation.Constraint;
import javax.validation.Payload;
import umc.spring.validation.validator.FoodTypeExistValidator;

@Documented
@Constraint(validatedBy = FoodTypeExistValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
public @interface ExistFoodType {
    String message() default "해당하는 음식종류가 존재하지 않습니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

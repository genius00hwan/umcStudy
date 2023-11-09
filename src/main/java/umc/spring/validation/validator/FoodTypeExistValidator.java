package umc.spring.validation.validator;


import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.repository.FoodTypeRepository;
import umc.spring.validation.annotation.ExistFoodType;

@Component
@RequiredArgsConstructor
public class FoodTypeExistValidator implements ConstraintValidator<ExistFoodType, List<Long>> {
    private final FoodTypeRepository foodTypeRepository;

    @Override
    public void initialize(ExistFoodType constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(List<Long> values, ConstraintValidatorContext context) {
        boolean isValid = values.stream()
                .allMatch(value -> foodTypeRepository.existsById(value));

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.FOOD_TYPE_NOT_FOUND.toString()).addConstraintViolation();
        }

        return isValid;

    }
}

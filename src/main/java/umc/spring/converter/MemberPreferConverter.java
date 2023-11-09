package umc.spring.converter;

import java.util.List;
import java.util.stream.Collectors;
import umc.spring.domain.FoodType;
import umc.spring.domain.mapping.MemberFoodType;

public class MemberPreferConverter {
    public static List<MemberFoodType> toMemberPreferList(List<FoodType> foodCategoryList){

        return foodCategoryList.stream()
                .map(foodCategory ->
                        MemberFoodType.builder()
                                .foodType(foodCategory)
                                .build()
                ).collect(Collectors.toList());
    }
}

package umc.spring.service.memberService;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.FoodTypeHandler;
import umc.spring.converter.MemberConverter;
import umc.spring.converter.MemberPreferConverter;
import umc.spring.domain.FoodType;
import umc.spring.domain.Member;
import umc.spring.domain.mapping.MemberFoodType;
import umc.spring.repository.FoodTypeRepository;
import umc.spring.repository.MemberRepository;
import umc.spring.web.dto.MemberRequest;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberCommandServiceImpl implements MemberCommandService{
    private final MemberRepository memberRepository;

    private final FoodTypeRepository foodTypeRepository;

    @Override
    @Transactional
    public Member joinMember(MemberRequest.Join request) {

        Member newMember = MemberConverter.toMember(request);
        List<FoodType> foodCategoryList = request.getPreferFoodType().stream()
                .map(category -> {
                    return foodTypeRepository.findById(category).orElseThrow(() -> new FoodTypeHandler(
                            ErrorStatus.FOOD_TYPE_NOT_FOUND));
                }).collect(Collectors.toList());

        List<MemberFoodType> memberFoodTypeList = MemberPreferConverter.toMemberPreferList(foodCategoryList);

        memberFoodTypeList.forEach(memberFoodType -> {memberFoodType.setMember(newMember);});

        return memberRepository.save(newMember);
    }
}

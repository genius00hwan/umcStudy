package umc.spring.service.memberService;

import umc.spring.domain.Member;
import umc.spring.web.dto.MemberRequest;

public interface MemberCommandService {
    Member joinMember(MemberRequest.Join request);
}

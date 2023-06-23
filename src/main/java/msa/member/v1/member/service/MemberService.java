package msa.member.v1.member.service;

import lombok.RequiredArgsConstructor;
import msa.member.v1.member.dao.MemberDao;
import msa.member.v1.member.dto.MemberGetDto;
import msa.member.v1.member.dto.MemberInDto;
import msa.member.v1.member.model.Member;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberDao memberDao;

    public Member login(MemberGetDto getDto) {
        Member member = memberDao.login(getDto);
        return member;
    }

    public int signUp(MemberInDto inDto) {
        int resultCount = memberDao.signUp(inDto);
        return resultCount;
    }
}

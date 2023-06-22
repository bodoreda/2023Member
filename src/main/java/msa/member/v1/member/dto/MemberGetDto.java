package msa.member.v1.member.dto;

import lombok.Data;
import msa.member.v1.member.model.Member;

import java.util.List;

@Data
public class MemberGetDto {
    private String loginId;
    private String encPw;
    private Member member;
    private List<Member> memberList;
}

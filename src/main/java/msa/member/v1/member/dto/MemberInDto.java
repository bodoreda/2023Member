package msa.member.v1.member.dto;

import lombok.Data;

@Data
public class MemberInDto {
    private String loginId;
    private String encPw;
    private String userName;
    private String phone;
    private String email;
    private String addr;
    private String addrDtl;
}

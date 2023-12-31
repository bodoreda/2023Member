package msa.member.v1.member.dto;

import lombok.Data;
import msa.member.v1.member.model.UserInfo;

import java.util.List;

@Data
public class MemberGetDto {
    private String loginId;
    private String encPw;
    private UserInfo userInfo;
    private String accessToken;
    private List<UserInfo> memberList;
}

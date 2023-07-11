package msa.member.v1.member.dto;

import lombok.Data;
import msa.member.v1.member.model.UserInfo;

import java.util.List;

@Data
public class MemberOutDto {
    private UserInfo userInfo;
    private String accessToken;
}

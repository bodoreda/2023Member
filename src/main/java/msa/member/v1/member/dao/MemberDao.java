package msa.member.v1.member.dao;

import msa.member.v1.member.dto.MemberGetDto;
import msa.member.v1.member.dto.MemberInDto;
import msa.member.v1.member.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDao {
    UserInfo login(MemberGetDto getDto);

    int signUp(MemberInDto inDto);
}

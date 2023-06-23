package msa.member.v1.member.dao;

import msa.member.v1.member.dto.MemberGetDto;
import msa.member.v1.member.dto.MemberInDto;
import msa.member.v1.member.model.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDao {
    Member login(MemberGetDto getDto);

    int signUp(MemberInDto inDto);
}

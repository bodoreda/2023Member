package msa.member.v1.member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import msa.member.v1.member.common.jwt.JwtTokenProvider;
import msa.member.v1.member.common.redis.RedisUtils;
import msa.member.v1.member.dao.MemberDao;
import msa.member.v1.member.dto.MemberGetDto;
import msa.member.v1.member.dto.MemberInDto;
import msa.member.v1.member.dto.MemberOutDto;
import msa.member.v1.member.model.UserInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Log4j2
public class MemberService {
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisUtils redisUtils;
    private final MemberDao memberDao;

    @Value("${spring.jwt.token.refreshExpTime}")
    private long refreshExpirationTime;

    public MemberOutDto login(MemberGetDto getDto) {
        UserInfo userInfo = memberDao.login(getDto);
        MemberOutDto outDto = new MemberOutDto();

        if(userInfo != null) {
            String accessToken = jwtTokenProvider.createAccessToken(userInfo.getCuid(), userInfo.getRoles());
            String refreshToken = jwtTokenProvider.createRefreshToken(userInfo.getCuid(), userInfo);
            // Redis에 refreshToken, UserInfo 저장
            redisUtils.setData("refreshToken", refreshToken, refreshExpirationTime);
            redisUtils.setUserInfo("userInfo", userInfo, refreshExpirationTime);

            outDto.setAccessToken(accessToken);
            
            // 로그인 성공 직후 보여줄 회원정보이나 세션 및 쿠키에 저장되지는 않을 휘발성 데이터
            outDto.setUserInfo(userInfo);
        }
        return outDto;
    }

    public int signUp(MemberInDto inDto) {
        int resultCount = memberDao.signUp(inDto);
        return resultCount;
    }
}

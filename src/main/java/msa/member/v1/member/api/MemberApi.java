package msa.member.v1.member.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import msa.member.v1.member.common.redis.RedisUtils;
import msa.member.v1.member.dto.MemberGetDto;
import msa.member.v1.member.dto.MemberInDto;
import msa.member.v1.member.dto.MemberOutDto;
import msa.member.v1.member.model.UserInfo;
import msa.member.v1.member.service.MemberService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/member")
@RequiredArgsConstructor
@Log4j2
public class MemberApi {

    private final MemberService memberService;
    private final RedisUtils redisUtils;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody MemberGetDto getDto) {
        log.traceEntry("{}", getDto);
        MemberOutDto outDto = memberService.login(getDto);
        log.traceExit(outDto);

        if(outDto.getAccessToken() != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + outDto.getAccessToken());
            return ResponseEntity.status(HttpStatus.OK).headers(headers).body(outDto);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("로그인 실패!");
        }
    }

    @PostMapping("/signUp")
    public ResponseEntity signUp(@RequestBody MemberInDto inDto) {
        log.traceEntry("{]", inDto);
        int resultCount = memberService.signUp(inDto);
        log.traceExit(resultCount);
        return ResponseEntity.status(HttpStatus.OK).body(resultCount);
    }

    @PostMapping("/getUserInfo")
    public UserInfo getUserInfo(@RequestHeader("Authorization") String authorization) {
        log.info("The Authorization in HttpHeader : {}", authorization);
        return redisUtils.getUserInfo("userInfo");
    }
}

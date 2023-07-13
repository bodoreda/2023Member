package msa.member.v1.member.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import msa.member.v1.member.dto.MemberGetDto;
import msa.member.v1.member.dto.MemberInDto;
import msa.member.v1.member.dto.MemberOutDto;
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

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody MemberGetDto getDto) {
        log.traceEntry("{}", getDto);
        HttpStatus returnStatus = HttpStatus.OK;

        MemberOutDto outDto = memberService.login(getDto);
        log.traceExit(outDto);

        if(outDto.getAccessToken() != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + outDto.getAccessToken());
            return ResponseEntity.status(returnStatus).headers(headers).body("로그인 성공!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("로그인 실패!");
        }
    }

    @PostMapping("/signUp")
    public ResponseEntity signUp(@RequestBody MemberInDto inDto) {
        log.traceEntry("{]", inDto);
        HttpStatus returnStatus = HttpStatus.OK;
        int resultCount = memberService.signUp(inDto);
        log.traceExit(resultCount);
        return ResponseEntity.status(returnStatus).body(resultCount);
    }

}

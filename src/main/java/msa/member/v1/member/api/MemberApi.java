package msa.member.v1.member.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import msa.member.v1.member.dto.MemberGetDto;
import msa.member.v1.member.model.Member;
import msa.member.v1.member.service.MemberService;
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
        Member member = memberService.login(getDto);
        getDto.setMember(member);
        log.traceExit(getDto);
        return ResponseEntity.status(returnStatus).body(getDto);
    }

}

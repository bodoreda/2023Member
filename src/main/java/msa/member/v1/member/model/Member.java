package msa.member.v1.member.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Member {
    @JsonIgnore
    private String cuid;
    @JsonProperty("loginId")
    private String login_id;
    @JsonProperty("userName")
    private String user_name;
    private String phone;
    private String email;
    private String addr;
    @JsonProperty("addrDtl")
    private String addr_dtl;
}

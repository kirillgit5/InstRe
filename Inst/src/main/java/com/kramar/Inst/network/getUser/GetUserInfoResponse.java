package com.kramar.Inst.network.getUser;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetUserInfoResponse {
    private Long id;
    private String nickname;
    private String firstname;
    private String lastname;
}

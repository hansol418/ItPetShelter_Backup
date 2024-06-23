package com.itpetshelter.itpetshelter.dto.login;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConsumerDTO {
    private String Cid;
    private String Cpw;
    private String Cname;
    private String Cphone;
}

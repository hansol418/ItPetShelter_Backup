package com.itpetshelter.itpetshelter.dto.login;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ManagerDTO {
    private String Mid;
    private String Mpw;
    private String Mname;
}

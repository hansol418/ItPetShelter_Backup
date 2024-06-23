package com.itpetshelter.itpetshelter.domain;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Consumer {
    @Id
    private String Cid;
    private String Cpw;
    private String Cname;
    private String Cphone;
}
package com.siliconspectra.management.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDegree {
    private String bachelor;
    private String bachelorGraduate;
    private String master;
    private String masterGraduate;
    private String phd;
    private String phdGraduate;
}

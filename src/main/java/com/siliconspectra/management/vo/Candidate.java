package com.siliconspectra.management.vo;

import com.siliconspectra.management.Entity.UserDegree;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Candidate {
    private String candidateName;
    private String candidateEmail;
    private String candidatePhoneNumber;
    private String candidateMarketingName;
    private String candidateMarketingEmail;
    private String candidateMarketingPhoneNumber;
    private String candidateLinkedin;
    private UserDegree degree;
    private String candidateGender;
    private String candidateVisaType;
    private String candidateBirthday;
    private String candidateLocation;
}

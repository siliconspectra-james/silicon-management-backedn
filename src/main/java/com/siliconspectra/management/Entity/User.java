package com.siliconspectra.management.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("User")
public class User {
    private String userId;
    private String userName;
    private String userEmail;
    private String userGender;
    private String userPhoneNumber;
    private String userMarketingName;
    private String userMarketingEmail;
    private String userMarketingPhoneNumber;
    private String userRole;
    private String userBatch;
    private String userBirthday;
    private UserDegree userDegree;
    private String userVisaType;
    private String userLocation;
    private String userLinkedin;
    private String userResume;
    private String userComments;
    private String version;
}

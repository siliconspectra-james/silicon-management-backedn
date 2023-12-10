package com.siliconspectra.management.auth;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class BasicAuthTest {

    @BeforeAll
    public static void init() throws IOException {
        Resource config = new ClassPathResource("silicon-spectra-management-firebase-adminsdk-mxlyv-230feab587.json");
        FileInputStream serviceAccount =
                new FileInputStream(config.getFile());

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        FirebaseApp.initializeApp(options);

    }

    //test admin user
    @Test
    public void getAdminAccessToken() throws IOException, FirebaseAuthException {
        String uid = "mkbBVm0sAWUvg8mzGRDsVLK5CyF2";
        String customToken = FirebaseAuth.getInstance().createCustomToken(uid);
        System.out.println(customToken);
    }

    @Test
    public void getCandidateAccessToken() throws IOException, FirebaseAuthException {
        String uid = "JC5XimdwmNZhCxvUZ7isoZcWYEY2";
        String customToken = FirebaseAuth.getInstance().createCustomToken(uid);
        System.out.println(customToken);
    }

    @Test
    public void addAdminAccess() throws IOException, FirebaseAuthException {
        Map<String, Object> claims = new HashMap<>();
        claims.put("admin", true);
        String uid = "mkbBVm0sAWUvg8mzGRDsVLK5CyF2";
        FirebaseAuth.getInstance().setCustomUserClaims(uid, claims);
    }
}

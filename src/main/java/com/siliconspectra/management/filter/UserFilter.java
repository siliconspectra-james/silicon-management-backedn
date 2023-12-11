package com.siliconspectra.management.filter;



import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Component
@WebFilter
public class UserFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        String idToken = request.getHeader("auth");
        //String idToken = request.getHeader("auth").split(" ")[1];
        String url = request.getRequestURI();
        if ("/login".equals(url) || "/".equals(url) || url.contains("health")) {
            filterChain.doFilter(servletRequest, servletResponse);
        }else if (url.contains("admin")){
            try {
                FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
                if (Boolean.TRUE.equals(decodedToken.getClaims().get("admin"))) {
                    filterChain.doFilter(servletRequest, servletResponse);
                }else {
                    throw new RuntimeException();
                }
            } catch (FirebaseAuthException e) {
                throw new RuntimeException();
            }
        }else if (url.contains("candidate")){
            try {
                FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
                String uid = decodedToken.getUid();
                String[] strs = request.getRequestURI().split("/");
                if (uid.equals(strs[strs.length - 1])) {
                    filterChain.doFilter(servletRequest, servletResponse);
                }else {
                    throw new RuntimeException();
                }
            } catch (FirebaseAuthException e) {
                throw new RuntimeException();
            }
        }
    }
}

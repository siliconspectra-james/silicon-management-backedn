package com.siliconspectra.management.Controller;


import com.siliconspectra.management.vo.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
@CrossOrigin(origins = "*")
public class HealthController {

    @GetMapping("/check")
    public ResponseEntity<Response> healthCheck() {
        return new ResponseEntity<>(new Response("available"), HttpStatus.OK);
    }
}

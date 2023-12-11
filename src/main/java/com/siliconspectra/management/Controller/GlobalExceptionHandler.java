package com.siliconspectra.management.Controller;

import com.siliconspectra.management.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler({ CustomException.class })
        public ResponseEntity handleException() {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

}

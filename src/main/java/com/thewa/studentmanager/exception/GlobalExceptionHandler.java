package com.thewa.studentmanager.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
   @ExceptionHandler(Exception.class)
   public ResponseEntity<Map<String, Object>> handleException(Exception ex) {
	  Map<String, Object> errorDetails = new HashMap<>();
	  errorDetails.put("message", ex.getMessage());
	  errorDetails.put("stackTrace", Arrays.toString(ex.getStackTrace()));
	  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
   }
}
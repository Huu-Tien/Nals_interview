package com.me.nalsinterview.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public class ResponseHandler {

    /**
     * Generate response
     *
     * @param data
     * @param status
     * @param message
     * @return ResponseEntity<Object>
     */
    public static ResponseEntity<Object> generateResponse(Object data, HttpStatus status, String message) {
        Response<Object> response = new Response<>();
        response.setData(data);
        response.setMessage(message);
        response.setStatus(status.value());
        response.setResultTime(LocalDateTime.now());
        return new ResponseEntity<Object>(response, status);
    }
}

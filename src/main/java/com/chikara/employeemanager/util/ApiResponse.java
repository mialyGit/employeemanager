package com.chikara.employeemanager.util;

import java.io.Serializable;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiResponse<T> implements Serializable {
    
    public static final String DEFAULT_MESSAGE = "Success";
    public static final HttpStatus DEFAULT_STATUS = HttpStatus.OK;

    private String message;
    private T data;
    
    public ApiResponse(String message, T data) {
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> ResponseEntity<ApiResponse<T>> send(T data) {
        ApiResponse<T> apiResponse = new ApiResponse<>(DEFAULT_MESSAGE, data);
        return new ResponseEntity<>(apiResponse, DEFAULT_STATUS);
    }

    public static <T> ResponseEntity<ApiResponse<T>> send(T data, String message) {
        ApiResponse<T> apiResponse = new ApiResponse<>(message, data);
        return new ResponseEntity<>(apiResponse, DEFAULT_STATUS);
    }

    public static <T> ResponseEntity<ApiResponse<T>> send(T data, String message, HttpStatus status) {
        ApiResponse<T> apiResponse = new ApiResponse<>(message, data);
        return new ResponseEntity<>(apiResponse, status);
    }
}

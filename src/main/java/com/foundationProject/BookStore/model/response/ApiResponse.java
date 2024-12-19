package com.foundationProject.BookStore.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {
    private int code;
    private String message;
    Object data;

    public static ApiResponse success(Object data){
        return new ApiResponse(200, null ,data);
    }

    public static ApiResponse error (int code, String message){
        return new ApiResponse(code, message,null);
    }

}

package com.specificationstructure.specificationstructure.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse {
    private HttpStatus status;
    private String message;
    private String error;
    private Object data;

    public ApiResponse(HttpStatus status, String message,String error)
    {
        this.status=status;
        this.message=message;
        this.error=error;
    }
    public ApiResponse(HttpStatus status, String message, Object data)
    {
        this.status=status;
        this.message=message;
        this.data=data;
    }
    public ApiResponse(HttpStatus status, String message)
    {
        this.status=status;
        this.message=message;
    }
}

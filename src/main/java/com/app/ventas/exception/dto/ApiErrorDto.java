package com.app.ventas.exception.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiErrorDto{
    private String message;
    private String backMessage;
    private String method;
    private String url;
}

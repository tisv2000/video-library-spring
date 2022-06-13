package com.tisv2000.http.handler;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice(basePackages = "com.tisv2000.http.rest")
public class RestControllerExceptionHandler extends ResponseEntityExceptionHandler {

}

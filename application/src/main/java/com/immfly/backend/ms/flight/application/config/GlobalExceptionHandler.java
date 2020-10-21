package com.immfly.backend.ms.flight.application.config;

import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  @ExceptionHandler(value = HttpClientErrorException.Unauthorized.class)
  public ErrorResponse handleUnauthorizedException(HttpServletRequest request,
      HttpClientErrorException.Unauthorized e) {
    return handleException(e, "Unauthorized", HttpStatus.UNAUTHORIZED);
  }

  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(value = Exception.class)
  public ErrorResponse handleAllException(HttpServletRequest request, Exception e) {
    return handleException(e, "Global_error", HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
  @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
  public ErrorResponse handleHttpRequestMethodNotSupportedException(HttpServletRequest request,
      HttpRequestMethodNotSupportedException e) {
    return handleException(e, "Method_not_allowed", HttpStatus.METHOD_NOT_ALLOWED);
  }

  private ErrorResponse handleException(Exception e, String type, HttpStatus status) {
    log.error(e.getMessage(), e);
    return buildErrorResponseFromException(e, type, status.toString());
  }

  private ErrorResponse buildErrorResponseFromException(Exception e, String type, String status) {
    return ErrorResponse.builder()
        .errorDescription(e.getMessage())
        .erroType(type)
        .errorCode(status)
        .build();
  }

}

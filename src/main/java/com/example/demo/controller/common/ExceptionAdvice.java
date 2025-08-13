package com.example.demo.controller.common;

import com.example.demo.controller.common.exceptions.BaseException;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.spi.LoggingEventBuilder;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.List;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(BaseException.class)
    @ApiResponses(value = {
//          @ApiResponse(responseCode = "400", description = "잘못된 요청", content = @Content(schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청", content = @Content),
            @ApiResponse(responseCode = "401", description = "인증 실패", content = @Content),
            @ApiResponse(responseCode = "403", description = "권한 없음", content = @Content),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스", content = @Content),
            @ApiResponse(responseCode = "409", description = "데이터 충돌", content = @Content),
            @ApiResponse(responseCode = "500", description = "서버 내부 에러", content = @Content)
    })
    public ResponseEntity<ProblemDetail> handle(BaseException exception) {
        HttpStatus status = exception.getStatus();
        String message = exception.getMessage();
        LoggingEventBuilder logger = switch (status.series()) {
            case CLIENT_ERROR -> log.atWarn();
            case SERVER_ERROR -> log.atError();
            default -> throw new IllegalStateException("에러에 대한 처리를 위해서는 오류 상태만을 받습니다 : " + status.series());
        };
        logger
                .setCause(exception)
                .log(message);
        return ResponseEntity
                .status(status)
                .body(this.build(status, message));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ApiResponse(responseCode = "400", description = "잘못된 요청", content = @Content)
    public ProblemDetail handle(MethodArgumentNotValidException exception) {
        String message = this.stringify(exception);
        log.warn(message, exception);
        return this.build(HttpStatus.BAD_REQUEST, message);
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ApiResponse(responseCode = "400", description = "잘못된 요청", content = @Content)
    public ProblemDetail handle(HandlerMethodValidationException exception) {
        String message = this.stringify(exception);
        log.warn(message, exception);
        return this.build(HttpStatus.BAD_REQUEST, message);
    }

    @ExceptionHandler({
            NoHandlerFoundException.class,
            NoResourceFoundException.class
    })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스", content = @Content)
    public ProblemDetail notFoundException(Exception exception) {
        String message = exception.getMessage();
        log.warn(message, exception);
        return this.build(HttpStatus.NOT_FOUND, message);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ApiResponse(responseCode = "500", description = "서버 내부 에러", content = @Content)
    public ProblemDetail notHandledException(Exception exception) {
        String message = exception.getMessage();
        log.error(message, exception);
        return this.build(HttpStatus.INTERNAL_SERVER_ERROR, message);
    }

    private ProblemDetail build(HttpStatus status, String message) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, message);
        problemDetail.setTitle(status.getReasonPhrase());
        return problemDetail;
    }

    private String stringify(MethodArgumentNotValidException exception) {
        StringBuilder errorMessageBuilder = new StringBuilder();
        for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
            errorMessageBuilder.append(fieldError.getDefaultMessage()).append("\n");
        }
        errorMessageBuilder.deleteCharAt(errorMessageBuilder.length() - 1);
        return errorMessageBuilder.toString();
    }

    private String stringify(HandlerMethodValidationException exception) {
        List<String> errorMessages = exception.getAllErrors()
                .stream()
                .map(MessageSourceResolvable::getDefaultMessage)
                .toList();
        StringBuilder errorMessageBuilder = new StringBuilder();
        for (String message : errorMessages) {
            errorMessageBuilder.append(message).append("\n");
        }
        errorMessageBuilder.deleteCharAt(errorMessageBuilder.length() - 1);
        return errorMessageBuilder.toString();
    }
}
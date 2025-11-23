package org.assignment.inventoryofreservedproducts.Exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;
import java.util.List;

@ControllerAdvice
public class DefaultExceptionHandler {


    @ExceptionHandler(VersionNotCorrectException.class)
    public ResponseEntity<ApiError> handleVersionNotCorrectException(
            VersionNotCorrectException e,
            HttpServletRequest request
    ) {

        HttpStatus status = HttpStatus.CONFLICT;

        ApiError apiError = new ApiError(
                request.getRequestURI(),
                e.getMessage(),
                status.value(),
                ZonedDateTime.now(),
                List.of()
        );
        return new ResponseEntity<>(apiError, status);
    }


    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiError> handleIllegalArgumentException(
            IllegalArgumentException e,
            HttpServletRequest request
    ) {

        HttpStatus status = HttpStatus.BAD_REQUEST;

        ApiError apiError = new ApiError(
                request.getRequestURI(),
                e.getMessage(),
                status.value(),
                ZonedDateTime.now(),
                List.of()
        );

        return new ResponseEntity<>(apiError, status);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGenericException(
            Exception e,
            HttpServletRequest request
    ) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        System.err.println("Unexpected server error: " + e);

        ApiError apiError = new ApiError(
                request.getRequestURI(),
                "Server error: " + e.getMessage(),
                status.value(),
                ZonedDateTime.now(),
                List.of()
        );

        return new ResponseEntity<>(apiError, status);
    }
}
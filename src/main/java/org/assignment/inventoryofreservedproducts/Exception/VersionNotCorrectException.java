package org.assignment.inventoryofreservedproducts.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class VersionNotCorrectException extends RuntimeException {
    public VersionNotCorrectException(String message) {
        super(message);
    }
}
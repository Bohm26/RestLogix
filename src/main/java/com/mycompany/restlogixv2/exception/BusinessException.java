
package com.mycompany.restlogixv2.exception;

/**
 *
 * @author joaopedro
 */

public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}
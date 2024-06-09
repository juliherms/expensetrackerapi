package com.github.juliherms.expensetrackerapi.exceptions.handler;

import com.github.juliherms.expensetrackerapi.entity.ErrorObject;
import com.github.juliherms.expensetrackerapi.exceptions.ItemAlreadyExistsException;
import com.github.juliherms.expensetrackerapi.exceptions.ResourceNotFoundException;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorObject> handleGeneralException(Exception ex, WebRequest request) {
        ErrorObject eObject = new ErrorObject();

        eObject.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        eObject.setMessage(ex.getMessage());
        eObject.setTimestamp(new Date());

        return new ResponseEntity<ErrorObject>(eObject,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ItemAlreadyExistsException.class)
    public ResponseEntity<Object> handleItemExistsException(ItemAlreadyExistsException ex, WebRequest request) {
        ErrorObject eObject = new ErrorObject();

        eObject.setStatusCode(HttpStatus.CONFLICT.value());
        eObject.setMessage(ex.getMessage());
        eObject.setTimestamp(new Date());

        return new ResponseEntity<Object>(eObject,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleException(MethodArgumentTypeMismatchException ex, WebRequest request) {
        ErrorObject eObject = new ErrorObject();

        eObject.setStatusCode(HttpStatus.BAD_REQUEST.value());
        eObject.setMessage(ex.getMessage());
        eObject.setTimestamp(new Date());

        return new ResponseEntity<Object>(eObject, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorObject> handleExpenseNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ErrorObject errorObject = new ErrorObject();

        errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorObject.setMessage(ex.getMessage());
        errorObject.setTimestamp(new Date());

        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request){
        Map<String, Object> body = new HashMap<String,Object>();
        body.put("timestamp", new Date());
        body.put("statusCode", HttpStatus.BAD_REQUEST);

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        body.put("messages", errors);

        return new ResponseEntity<Object>(body, HttpStatus.BAD_REQUEST);
    }
}

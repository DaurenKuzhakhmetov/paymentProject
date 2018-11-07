package testpay.exceptions;

import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.lang.Nullable;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/*
 see  :   http://www.springboottutorial.com/spring-boot-exception-handling-for-rest-services
 */
@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandle extends ResponseEntityExceptionHandler {

      @ExceptionHandler(Internal_Server_Error.class)
    public final ResponseEntity<ExceptionEntity> handleServerError(Internal_Server_Error ex,WebRequest request){
        ExceptionEntity exceptionEntity = new ExceptionEntity("500","An internal server error has occurred");
        return new ResponseEntity<>(exceptionEntity,HttpStatus.INTERNAL_SERVER_ERROR);
    }



    @ExceptionHandler(InvalidRequest.class)
    public final ResponseEntity<ExceptionEntity> handleInvalidRequest(InvalidRequest ex,WebRequest request){
        ExceptionEntity exceptionEntity  = new ExceptionEntity("400","Request is not well-formatted syntactically incorrect or violates schema");
        return new ResponseEntity<>(exceptionEntity,HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ExceptionEntity exceptionEntity = new ExceptionEntity("415","The server does not support the request payload media type");
        return new ResponseEntity<>(exceptionEntity,HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }


}
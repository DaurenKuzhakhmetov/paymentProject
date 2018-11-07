package testpay.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class Internal_Server_Error extends RuntimeException{
      public Internal_Server_Error(){
           super();
      }
}

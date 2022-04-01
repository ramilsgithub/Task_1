package az.task.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> handleException(CourseNotFoundException exc){

        ErrorMessage error = new ErrorMessage(HttpStatus.NOT_FOUND.value(),exc.getMessage());

        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler
    public ResponseEntity<ErrorMessage> handleException(Exception exc){

        ErrorMessage error = new ErrorMessage(HttpStatus.BAD_REQUEST.value(),exc.getMessage());

        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }
}
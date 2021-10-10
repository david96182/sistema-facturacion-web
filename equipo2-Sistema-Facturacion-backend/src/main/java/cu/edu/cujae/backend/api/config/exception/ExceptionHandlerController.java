package cu.edu.cujae.backend.api.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerController {
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<?> runtimeException(RuntimeException e) {
		return new ResponseEntity<>("[Exception Response] - Exception: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}

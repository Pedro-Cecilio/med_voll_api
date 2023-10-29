package med.voll.api.infra.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import med.voll.api.infra.ResponseError;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import java.util.NoSuchElementException;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ResponseError> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        return ResponseEntity.status(409).body(new ResponseError(409, e.getMostSpecificCause().getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ResponseError>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        // String[] partes = e.getMessage().split("]");
        // String resultado = partes[0]+"]";
        var errors = e.getFieldErrors().stream().map(ResponseError::new).toList();
        return ResponseEntity.status(400).body(errors);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseError> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return ResponseEntity.status(400).body(new ResponseError(400, e.getCause().getMessage()));
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ResponseError> handleNoSuchElementException(NoSuchElementException e) {
        return ResponseEntity.status(404).body(new ResponseError(400,
                "Element not found"));
    }
}

package med.voll.api.infra;

import org.springframework.validation.FieldError;

public record ResponseError(long statusCode, Object cause) {
    public ResponseError(FieldError error) {
        this(400, error.getField() + ": " + error.getDefaultMessage());
    }
}

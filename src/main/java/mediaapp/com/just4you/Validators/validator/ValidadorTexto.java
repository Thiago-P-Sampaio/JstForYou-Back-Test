package mediaapp.com.just4you.Validators.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import mediaapp.com.just4you.Validators.annotations.Texto;

public class ValidadorTexto implements ConstraintValidator<Texto, String> {

    @Override
    public boolean isValid(String valor, ConstraintValidatorContext context) {
        if (valor == null) {
            return true;
        }
        return valor.matches("^[a-zA-ZÀ-ú\\s]+$");
    }
}

package mediaapp.com.just4you.Validators.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import mediaapp.com.just4you.Validators.validator.ValidadorTexto;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ValidadorTexto.class)
public @interface Texto {
    String message() default "Formato de texto inválido!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

package mediaapp.com.just4you.Validators.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import mediaapp.com.just4you.Validators.validator.ValidadorDominio;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidadorDominio.class)
public @interface DominioValido {

    String message() default "A URL não pertence a um domínio permitido";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String[] dominiosPermitidos();
    String[] protocolosPermitidos();
}

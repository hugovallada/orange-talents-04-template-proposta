package com.br.zupacademy.hugo.proposta.util.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD,ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueValueValidation.class)
public @interface UniqueValue {
    Class<?> targetClass();
    String campo();
    String message() default "O valor não é único";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}

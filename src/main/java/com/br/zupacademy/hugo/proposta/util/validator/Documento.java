package com.br.zupacademy.hugo.proposta.util.validator;

import org.hibernate.validator.constraints.CompositionType;
import org.hibernate.validator.constraints.ConstraintComposition;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.CONSTRUCTOR})
@ConstraintComposition(CompositionType.OR)
@CPF
@CNPJ
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
public @interface Documento {
    String message() default "O valor passado não corresponde a um documento válido";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };

}

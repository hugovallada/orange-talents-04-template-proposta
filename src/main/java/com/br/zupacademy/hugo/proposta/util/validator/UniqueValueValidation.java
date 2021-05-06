package com.br.zupacademy.hugo.proposta.util.validator;

import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class UniqueValueValidation implements ConstraintValidator<UniqueValue, String> {

    private String campo;
    private Class<?> targetClass;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void initialize(UniqueValue constraintAnnotation) {
        campo = constraintAnnotation.campo();
        targetClass = constraintAnnotation.targetClass();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        List<?> resultList = entityManager.createQuery("SELECT 1 FROM " + targetClass.getName() + " where " + campo + " = :value")
                .setParameter("value", value).getResultList();

        Assert.state(resultList.size() <= 1, "O sistema não poderia ter 2 propostas com o mesmo documento");

        if(resultList.size() < 1) return true;
        throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Já existe uma entidade com este " + campo);
    }
}

package com.br.zupacademy.hugo.proposta.util.encriptor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class EncriptorConverter implements AttributeConverter<String, String> {

    TextEncryptor encryptor;

    public EncriptorConverter(@Value("${password.encryptor}") String pass, @Value("${salt.encryptor}") String salt){
        encryptor = Encryptors.text(pass, salt);
    }

    @Override
    public String convertToDatabaseColumn(String attribute) {
        return encryptor.encrypt(attribute);
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        return encryptor.decrypt(dbData);
    }
}

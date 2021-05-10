package com.br.zupacademy.hugo.proposta.util.transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class ExecutorTransacao {

    @Transactional
    public <T>T salvaEComita(T objeto, JpaRepository<T, ?> repository){
        return (T) repository.save(objeto);
    }
}

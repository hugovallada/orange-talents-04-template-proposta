package com.br.zupacademy.hugo.proposta.cartao;

import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CartaoScheduler {

    @Autowired
    private CartaoClient cartaoClient;

    @Scheduled(fixedRate = 100000)
    public void verificaSituacaoNoCartao(){
        try{
            var response = cartaoClient.consultaAnaliseCartao(3L);
            System.out.println(response.getId());
        }catch (FeignException exception){
            System.out.println("Cartão não encontrado");
        }


    }

}

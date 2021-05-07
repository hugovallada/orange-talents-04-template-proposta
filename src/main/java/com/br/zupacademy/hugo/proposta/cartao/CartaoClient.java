package com.br.zupacademy.hugo.proposta.cartao;

import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "cartao", url = "${api.cartao.url}")
public interface CartaoClient {

    @GetMapping("/api/contas/{id}")
    String consultaAnaliseCartao(@PathVariable Long id);
}

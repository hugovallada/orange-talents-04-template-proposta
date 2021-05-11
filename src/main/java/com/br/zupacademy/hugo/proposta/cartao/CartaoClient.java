package com.br.zupacademy.hugo.proposta.cartao;

import com.br.zupacademy.hugo.proposta.cartao.bloqueio.IdentificadorBloqueioFeign;
import com.br.zupacademy.hugo.proposta.cartao.bloqueio.ResponseBloqueioFeign;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "cartao", url = "${api.cartao.url}")
public interface CartaoClient {

    @GetMapping("/api/cartoes?idProposta")
    CartaoSituacaoResponse consultaAnaliseCartao(@RequestParam Long idProposta);

    @PostMapping("/api/cartoes/{idCartao}/bloqueios")
    ResponseBloqueioFeign avisaSobreBloqueioCartao(@PathVariable String idCartao, @RequestBody IdentificadorBloqueioFeign sistemaResponsavel);

}

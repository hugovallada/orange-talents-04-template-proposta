package com.br.zupacademy.hugo.proposta.cartao;

import com.br.zupacademy.hugo.proposta.cartao.aviso.NovoAvisoRequest;
import com.br.zupacademy.hugo.proposta.cartao.aviso.NovoAvisoRequestFeign;
import com.br.zupacademy.hugo.proposta.cartao.bloqueio.IdentificadorBloqueioFeign;
import com.br.zupacademy.hugo.proposta.cartao.bloqueio.ResponseBloqueioFeign;
import com.br.zupacademy.hugo.proposta.cartao.carteira.CarteiraResponseFeign;
import com.br.zupacademy.hugo.proposta.cartao.carteira.NovaCarteiraRequest;
import com.br.zupacademy.hugo.proposta.cartao.carteira.NovaCarteiraRequestFeign;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "cartao", url = "${api.cartao.url}")
public interface CartaoClient {

    @GetMapping("/api/cartoes?idProposta")
    CartaoSituacaoResponse consultaAnaliseCartao(@RequestParam Long idProposta);

    @PostMapping("/api/cartoes/{idCartao}/bloqueios")
    ResponseBloqueioFeign avisaSobreBloqueioCartao(@PathVariable String idCartao, @RequestBody IdentificadorBloqueioFeign sistemaResponsavel);

    @PostMapping("/api/cartoes/{idCartao}/avisos")
    String avisarSobreViagem(@PathVariable String idCartao, @RequestBody NovoAvisoRequestFeign avisoRequest);

    @PostMapping("/api/cartoes/{idCartao}/carteiras")
    CarteiraResponseFeign associarCarteira(@PathVariable String idCartao, @RequestBody NovaCarteiraRequestFeign carteiraRequest);
}

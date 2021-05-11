package com.br.zupacademy.hugo.proposta.cartao;

import com.br.zupacademy.hugo.proposta.cartao.bloqueio.Bloqueio;
import com.br.zupacademy.hugo.proposta.cartao.bloqueio.IdentificadorBloqueioFeign;
import com.br.zupacademy.hugo.proposta.cartao.bloqueio.ResponseBloqueioFeign;
import com.br.zupacademy.hugo.proposta.util.logger.LoggerUtil;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@RequestMapping("/cartoes")
public class CartaoController {

    private static final Logger LOG = LoggerFactory.getLogger(CartaoController.class);

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private CartaoClient cartaoClient;

    @PostMapping("/bloquear/{idCartao}")
    public ResponseEntity<Void> bloquearCartao(
            @RequestHeader("user-agent") String userAgent,
            HttpServletRequest request,
            @PathVariable String idCartao
            ){

        Optional<Cartao> cartaoOpt = cartaoRepository.findById(idCartao);

        if(cartaoOpt.isEmpty()) return ResponseEntity.notFound().build();

        Cartao cartao = cartaoOpt.get();

        if(cartao.estaBloqueado()) return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();

        String host = request.getRemoteAddr();

        Bloqueio bloqueio = new Bloqueio(cartao, host, userAgent);

        try{
            ResponseBloqueioFeign response = cartaoClient.avisaSobreBloqueioCartao(cartao.getId(), new IdentificadorBloqueioFeign(bloqueio.getSistemaResponsavel()));

            cartao.associaBloqueio(bloqueio);
            cartao.bloquearCartao();
            cartaoRepository.save(cartao);
            LOG.info("O cartão " + LoggerUtil.ofuscarDados(cartao.getId()) + " foi bloqueado");

        }catch (FeignException exception){
            System.out.println(exception.getMessage());
            System.out.println(exception.contentUTF8());
            LOG.error("Algo aconteceu, não foi posssível fazer o bloqueio");
        }
        return ResponseEntity.ok().build();
    }
}

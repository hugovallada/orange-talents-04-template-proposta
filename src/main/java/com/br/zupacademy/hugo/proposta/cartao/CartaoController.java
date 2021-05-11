package com.br.zupacademy.hugo.proposta.cartao;

import com.br.zupacademy.hugo.proposta.cartao.bloqueio.Bloqueio;
import com.br.zupacademy.hugo.proposta.util.logger.LoggerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@RequestMapping("/cartoes")
public class CartaoController {

    private static final Logger LOG = LoggerFactory.getLogger(CartaoController.class);

    @Autowired
    private CartaoRepository cartaoRepository;

    @PostMapping("/bloquear/{idCartao}")
    public ResponseEntity<Void> bloquearCartao(
            @RequestHeader("user-agent") String userAgent,
            HttpServletRequest request,
            @PathVariable String idCartao
            ){

        Optional<Cartao> cartaoOpt = cartaoRepository.findById(idCartao);

        if(cartaoOpt.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cartão não encontrado");

        Cartao cartao = cartaoOpt.get();

        if(cartao.estaBloqueado()) throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "O cartão já está bloqueado");

        String host = request.getRemoteAddr();

        Bloqueio bloqueio = new Bloqueio(cartao, host, userAgent);
        cartao.associaBloqueio(bloqueio);

        cartaoRepository.save(cartao);
        LOG.info("O cartão " + LoggerUtil.ofuscarDados(cartao.getId()) + " foi bloqueado");
        return ResponseEntity.ok().build();
    }
}

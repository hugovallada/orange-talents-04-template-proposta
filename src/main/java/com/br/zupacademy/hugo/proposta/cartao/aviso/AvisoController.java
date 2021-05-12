package com.br.zupacademy.hugo.proposta.cartao.aviso;

import com.br.zupacademy.hugo.proposta.cartao.Cartao;
import com.br.zupacademy.hugo.proposta.cartao.CartaoClient;
import com.br.zupacademy.hugo.proposta.cartao.CartaoRepository;
import com.br.zupacademy.hugo.proposta.util.logger.LoggerUtil;
import com.br.zupacademy.hugo.proposta.util.transaction.ExecutorTransacao;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static com.br.zupacademy.hugo.proposta.util.logger.LoggerUtil.ofuscarDados;

@RestController
@RequestMapping("/cartoes")
public class AvisoController {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private AvisoRepository avisoRepository;

    @Autowired
    private ExecutorTransacao executorTransacao;

    @Autowired
    private CartaoClient cartaoClient;

    private static final Logger LOG = LoggerFactory.getLogger(AvisoController.class);


    @PostMapping("/{idCartao}/avisos")
    public ResponseEntity<Void> cadastrarNovoAviso(@PathVariable String idCartao,
                                                   @RequestHeader("user-agent") String userAgent,
                                                   HttpServletRequest request,
                                                   @RequestBody @Valid NovoAvisoRequest avisoRequest){

        Cartao cartao = cartaoRepository.findById(idCartao).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cartão não encontrado"));
        // TODO: Confirmar no check-in se é permitido impedir que seja feito um aviso para um cartão bloqueado
        /**
         * Caso o cartão esteja bloqueado, não permite que tente lançar um aviso de viagem,
         * pois o cartão já está inutilizável.
         */
        if(cartao.estaBloqueado()) throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "O cartão está bloqueado");

        try{
            String response = cartaoClient.avisarSobreViagem(idCartao, new NovoAvisoRequestFeign(avisoRequest));

            Assert.hasText("CRIADO", "Isso não deveria ter acontecido");

            Aviso aviso = avisoRequest.toModel(cartao,userAgent, request.getRemoteAddr());
            executorTransacao.salvaEComita(aviso, avisoRepository);
            return ResponseEntity.ok().build();
        } catch (FeignException exception){

            LOG.info("Um erro aconteceu, não foi possível enviar o aviso de viagem para o cartão " + ofuscarDados(cartao.getId()));
        }

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();

    }
}

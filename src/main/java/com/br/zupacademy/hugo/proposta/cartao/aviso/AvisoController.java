package com.br.zupacademy.hugo.proposta.cartao.aviso;

import com.br.zupacademy.hugo.proposta.cartao.Cartao;
import com.br.zupacademy.hugo.proposta.cartao.CartaoRepository;
import com.br.zupacademy.hugo.proposta.util.transaction.ExecutorTransacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/cartoes")
public class AvisoController {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private AvisoRepository avisoRepository;

    @Autowired
    private ExecutorTransacao executorTransacao;


    @PostMapping("/{idCartao}/avisos")
    public ResponseEntity<Void> cadastrarNovoAviso(@PathVariable String idCartao,
                                                   @RequestHeader("user-agent") String userAgent,
                                                   HttpServletRequest request,
                                                   @RequestBody @Valid NovoAvisoRequest avisoRequest){

        Cartao cartao = cartaoRepository.findById(idCartao).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cartão não encontrado"));
        // TODO: Devo fazer a verificação se o cartão está bloqueado ?
        String ipClient = request.getRemoteAddr();

        Aviso aviso = avisoRequest.toModel(cartao, userAgent, ipClient);

        executorTransacao.salvaEComita(aviso, avisoRepository);

        return  ResponseEntity.ok().build();

    }
}

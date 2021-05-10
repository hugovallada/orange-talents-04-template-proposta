package com.br.zupacademy.hugo.proposta.cartao.biometria;

import com.br.zupacademy.hugo.proposta.cartao.CartaoClient;
import com.br.zupacademy.hugo.proposta.cartao.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/cartoes")
public class BiometriaController {

    @Autowired
    private BiometriaRepository biometriaRepository;

    @Autowired
    private CartaoRepository cartaoRepository;

    @PostMapping("biometria/cadastrar/{numeroCartao}")
    public ResponseEntity<Void> cadastrarBiometria(
            @RequestBody @Valid NovaBiometriaRequest biometriaRequest,
            @PathVariable String numeroCartao,
            UriComponentsBuilder componentsBuilder
    ) {

        var cartao = cartaoRepository.findById(numeroCartao);

        if(cartao.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não encontrado");
        }

        var biometria = biometriaRepository.save(biometriaRequest.toModel(cartao.get()));

        var uri = componentsBuilder.path("/biometria/{id}").buildAndExpand(biometria.getId().toString()).toUri();

        return ResponseEntity.created(uri).build();


    }
}

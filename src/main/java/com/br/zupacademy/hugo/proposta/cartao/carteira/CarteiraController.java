package com.br.zupacademy.hugo.proposta.cartao.carteira;

import com.br.zupacademy.hugo.proposta.cartao.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/cartoes")
public class CarteiraController {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private CarteiraRepository carteiraRepository;

    @PostMapping("/{idCartao}/carteiras")
    public ResponseEntity<Void> associarCarteira(@PathVariable String idCartao, @RequestBody @Valid NovaCarteiraRequest carteiraRequest){
        var cartao = cartaoRepository.findById(idCartao).orElseThrow(() -> new RuntimeException("Algo aconteceu"));

        var carteira = carteiraRequest.toModel(cartao);

        // Verifica se o cartão já está associado a esse emissor de carteira
        if(carteiraRepository.findByEmissorAndCartaoId(carteira.getEmissor(), idCartao).isPresent()){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Esse cartão já está associado a uma carteira desse tipo");
        }

        carteiraRepository.save(carteiraRequest.toModel(cartao));
        return ResponseEntity.ok().build();
    }


}

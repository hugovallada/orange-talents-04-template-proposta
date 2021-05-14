package com.br.zupacademy.hugo.proposta.proposta;

import com.br.zupacademy.hugo.proposta.cartao.CartaoClient;
import com.br.zupacademy.hugo.proposta.proposta.consulta.ConsultaPropostaClient;
import com.br.zupacademy.hugo.proposta.proposta.consulta.ConsultaPropostaRequest;
import com.br.zupacademy.hugo.proposta.proposta.consulta.ConsultaPropostaResponse;
import com.br.zupacademy.hugo.proposta.proposta.consulta.ResultadoSolicitacao;
import com.br.zupacademy.hugo.proposta.util.encriptor.EncriptorConverter;
import com.br.zupacademy.hugo.proposta.util.transaction.ExecutorTransacao;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

import java.util.stream.Collectors;

import static com.br.zupacademy.hugo.proposta.util.logger.LoggerUtil.ofuscarDados;

@RestController
@RequestMapping("/propostas")
public class PropostaController {

    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private ConsultaPropostaClient consultaPropostaClient;

    @Autowired
    private CartaoClient cartaoClient;

    @Autowired
    private ExecutorTransacao executorTransacao;

    private Logger logger = LoggerFactory.getLogger(PropostaController.class);

    @PostMapping
    public ResponseEntity<Void> cadastrarProposta(@RequestBody @Valid NovaPropostaRequest novaPropostaRequest,
                                                    UriComponentsBuilder uriComponentsBuilder){

        if(documentoDuplicado(novaPropostaRequest.getDocumento())){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Um problema aconteceu!Verifique o seu documento");
        }

        Proposta proposta = executorTransacao.salvaEComita(novaPropostaRequest.toModel(), propostaRepository);

        atualizarStatusProposta(proposta);

        return ResponseEntity.created(uriComponentsBuilder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri()).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<AcompanhamentoDePropostaResponse> acompanharProposta(@PathVariable Long id){
        Proposta proposta = propostaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Proposta com id " + id + "não encontrada"));
        return ResponseEntity.ok().body(new AcompanhamentoDePropostaResponse(proposta));
    }

    private void atualizarStatusProposta(Proposta proposta) {
        ConsultaPropostaRequest consulta = new ConsultaPropostaRequest(proposta);

        try{
            ConsultaPropostaResponse consultaResponse = consultaPropostaClient.solicitacao(consulta);
            proposta.atualizarSituacao(consultaResponse.getResultadoSolicitacao());
            executorTransacao.salvaEComita(proposta, propostaRepository);
            logger.info("Proposta de id " + consulta.getIdProposta() + " com documento " + ofuscarDados(consulta.getDocumento()) + " está elegível");
        } catch (FeignException.UnprocessableEntity exception){
            proposta.atualizarSituacao(ResultadoSolicitacao.COM_RESTRICAO);
            executorTransacao.salvaEComita(proposta, propostaRepository);
            logger.info("Proposta de id " + consulta.getIdProposta() + " com documento  " + ofuscarDados(consulta.getDocumento())  + " não está elegível");
        }
    }

    /**
     * Método criado para realizar a validação de documento único.
     * O método foi criado, para ser possível validar se o documento é único ao mesmo tempo que salva os dados criptografados no banco
     *
     * @param documento recebido no corpo da requisição
     * @return verifica se a lista buscada no banco possui um ou mais elementos
     */
    private boolean documentoDuplicado(String documento){
        return propostaRepository.findAll()
                .stream().filter(proposta -> proposta.getDocumento().equals(documento))
                .collect(Collectors.toList())
                .size() >= 1;
    }

}

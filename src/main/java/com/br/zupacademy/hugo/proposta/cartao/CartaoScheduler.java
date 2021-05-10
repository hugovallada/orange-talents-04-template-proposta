package com.br.zupacademy.hugo.proposta.cartao;

import com.br.zupacademy.hugo.proposta.proposta.PropostaRepository;
import com.br.zupacademy.hugo.proposta.proposta.Situacao;
import com.br.zupacademy.hugo.proposta.util.logger.LoggerUtil;
import com.br.zupacademy.hugo.proposta.util.transaction.ExecutorTransacao;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static com.br.zupacademy.hugo.proposta.util.logger.LoggerUtil.ofuscarDados;

@Component
public class CartaoScheduler {

    @Autowired
    private CartaoClient cartaoClient;

    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private ExecutorTransacao executorTransacao;

    private Logger logger = LoggerFactory.getLogger(CartaoScheduler.class);

    @Scheduled(fixedRateString = "${taxa.atualizacao.scheduler}")
    public void verificaSituacaoNoCartao(){
        var propostasAprovadas = propostaRepository.buscarPropostasElegiveisSemCartao();

        for(var proposta : propostasAprovadas){
            try{
                var response = cartaoClient.consultaAnaliseCartao(proposta.getId());
                proposta.associarNumeroDeCartao(response.getId());
                executorTransacao.salvaEComita(proposta, propostaRepository);
                var cartao = response.toModel();
                System.out.println(response);
                executorTransacao.salvaEComita(cartao, cartaoRepository);
                logger.info("Cartão " + ofuscarDados(proposta.getNumeroCartao()) + " associado a conta " + ofuscarDados(proposta.getDocumento()));
            }catch (FeignException exception){
                logger.info("Uma exceção aconteceu do lado do Feign na API de Cartões");
            }
        }



    }

}

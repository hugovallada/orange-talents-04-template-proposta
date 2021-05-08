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
    private ExecutorTransacao executorTransacao;

    private Logger logger = LoggerFactory.getLogger(CartaoScheduler.class);

    @Scheduled(fixedRate = 100000)
    public void verificaSituacaoNoCartao(){
        var propostasAprovadas = propostaRepository.buscarPropostasElegiveis();

        for(var proposta : propostasAprovadas){
            try{
                var response = cartaoClient.consultaAnaliseCartao(proposta.getId());
                proposta.associarNumeroDeCartao(response.getId());
                executorTransacao.salvaEComita(proposta, propostaRepository);
                logger.info("Cartão " + ofuscarDados(proposta.getDocumento()) + " associado a conta " + ofuscarDados(proposta.getDocumento()));
            }catch (FeignException exception){
                logger.info("Uma exceção aconteceu do lado do Feign na API de Cartões");
                 System.out.println("Cartão não encontrado");
            }
        }



    }

}

package com.br.zupacademy.hugo.proposta.proposta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PropostaRepository extends JpaRepository<Proposta, Long> {
    boolean existsByDocumento(String documento);

    @Query(value = "Select * from proposta where situacao = 'ELEGIVEL' and numero_cartao is null", nativeQuery = true)
    List<Proposta> buscarPropostasElegiveisSemCartao();

}

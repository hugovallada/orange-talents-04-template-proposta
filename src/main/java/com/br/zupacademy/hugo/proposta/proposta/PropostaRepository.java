package com.br.zupacademy.hugo.proposta.proposta;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta, Long> {
    boolean existsByDocumento(String documento);

    @Query(value = "Select * from proposta where situacao = 'ELEGIVEL' and numero_cartao = null", nativeQuery = true)
    List<Proposta> buscarPropostasElegiveis();
}

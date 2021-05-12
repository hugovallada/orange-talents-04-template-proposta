package com.br.zupacademy.hugo.proposta.cartao.carteira;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarteiraRepository extends JpaRepository<Carteira, Long> {

    Optional<Carteira> findByEmissorAndCartaoId(TipoDeCarteira emissor, String cartaoId);
}

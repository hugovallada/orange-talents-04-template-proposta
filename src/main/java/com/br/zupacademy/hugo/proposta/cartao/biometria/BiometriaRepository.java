package com.br.zupacademy.hugo.proposta.cartao.biometria;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BiometriaRepository extends CrudRepository<Biometria, Long> {
}

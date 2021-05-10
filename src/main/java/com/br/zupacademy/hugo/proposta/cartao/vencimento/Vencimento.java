package com.br.zupacademy.hugo.proposta.cartao.vencimento;

import com.br.zupacademy.hugo.proposta.cartao.Cartao;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Vencimento {
    @Id
    private String id;

    private Integer dia;

    private LocalDateTime dataCriacao;

    @OneToMany(mappedBy = "vencimento")
    private List<Cartao> cartoes;

    public Vencimento(String id, Integer dia, LocalDateTime dataCriacao) {
        this.id = id;
        this.dia = dia;
        this.dataCriacao = dataCriacao;
    }

    public Vencimento() {
    }
}

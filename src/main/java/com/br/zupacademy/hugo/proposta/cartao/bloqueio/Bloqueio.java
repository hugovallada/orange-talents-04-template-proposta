package com.br.zupacademy.hugo.proposta.cartao.bloqueio;

import com.br.zupacademy.hugo.proposta.cartao.Cartao;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Bloqueio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private LocalDateTime bloqueadoEm;

    private String sistemaResponsavel;

    private String ipCliente;

    private String userAgent;

    private boolean ativo;

    @ManyToOne
    private Cartao cartao;

    public Bloqueio(Long id, LocalDateTime bloqueadoEm, String sistemaResponsavel, boolean ativo) {
        this.id = id;
        this.bloqueadoEm = bloqueadoEm;
        this.sistemaResponsavel = sistemaResponsavel;
        this.ativo = ativo;
    }

    public Bloqueio(Cartao cartao, String ipCliente, String userAgent){
        this.cartao = cartao;
        this.ipCliente = ipCliente;
        this.userAgent = userAgent;
        this.ativo = true;
    }


    /**
     * @deprecated Uso exclusivo da Jpa
     */
    @Deprecated
    public Bloqueio() {
    }

    public boolean isAtivo() {
        return ativo;
    }
}

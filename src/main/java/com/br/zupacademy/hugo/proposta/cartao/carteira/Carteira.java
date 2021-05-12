package com.br.zupacademy.hugo.proposta.cartao.carteira;

import com.br.zupacademy.hugo.proposta.cartao.Cartao;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Carteira {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    @CreationTimestamp
    private LocalDateTime associadaEm;

    @Enumerated(EnumType.STRING)
    private TipoDeCarteira emissor;

    @ManyToOne
    private Cartao cartao;

    public Carteira( String email, TipoDeCarteira emissor, Cartao cartao) {
        this.email = email;
        this.emissor = emissor;
        this.cartao = cartao;
    }

    /**
     * @deprecated Uso exclusivo da Jpa
     */
    @Deprecated
    public Carteira() {
    }

    public TipoDeCarteira getEmissor() {
        return emissor;
    }
}

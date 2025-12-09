package com.gerenciadordedoacoes.gerenciamento_doacoes.Entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Getter
@Setter
@NoArgsConstructor
public class DoacaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipoDoacao;
    private Integer quantidade;
    private LocalDate data;
    private String observacoes;

    @Enumerated(EnumType.STRING)
    private StatusDoacao status;

    @ManyToOne
    @JoinColumn(name = "doador_id")
    @JsonIdentityReference(alwaysAsId = false)
    private DoadorEntity doador;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    @JsonIdentityReference(alwaysAsId = false)
    private PedidoEntity pedido;

    public enum StatusDoacao {
        PENDENTE,
        EM_ANDAMENTO,
        ENTREGUE,
        CANCELADA
    }
}

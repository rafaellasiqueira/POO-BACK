package com.gerenciadordedoacoes.gerenciamento_doacoes.Repository;

import com.gerenciadordedoacoes.gerenciamento_doacoes.Entity.PedidoEntity;
import com.gerenciadordedoacoes.gerenciamento_doacoes.Entity.InstituicaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PedidoRepository extends JpaRepository<PedidoEntity, Long> {
    List<PedidoEntity> findByInstituicao(InstituicaoEntity instituicao);
}

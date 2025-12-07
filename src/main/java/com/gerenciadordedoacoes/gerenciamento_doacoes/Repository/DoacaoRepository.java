package com.gerenciadordedoacoes.gerenciamento_doacoes.Repository;

import com.gerenciadordedoacoes.gerenciamento_doacoes.Entity.DoacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface DoacaoRepository extends JpaRepository<DoacaoEntity, Long> {

    @Query("SELECT d FROM DoacaoEntity d " +
            "JOIN FETCH d.doador " +
            "JOIN FETCH d.pedido p " +
            "JOIN FETCH p.instituicao i " +
            "WHERE i.id = :instituicaoId")
    List<DoacaoEntity> findByInstituicaoId(@Param("instituicaoId") Long instituicaoId);

    @Query("SELECT d FROM DoacaoEntity d " +
            "JOIN FETCH d.doador " +
            "LEFT JOIN FETCH d.pedido " +
            "WHERE d.id = :id")
    DoacaoEntity findByIdWithDoadorAndPedido(@Param("id") Long id);

    @Query("SELECT d FROM DoacaoEntity d " +
            "JOIN FETCH d.doador " +
            "LEFT JOIN FETCH d.pedido p " +
            "LEFT JOIN FETCH p.instituicao " +
            "WHERE d.doador.id = :idDoador")
    List<DoacaoEntity> findByDoadorId(@Param("idDoador") Long idDoador);
}

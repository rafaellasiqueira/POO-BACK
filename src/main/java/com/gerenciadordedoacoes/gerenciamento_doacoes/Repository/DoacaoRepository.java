package com.gerenciadordedoacoes.gerenciamento_doacoes.Repository;

import com.gerenciadordedoacoes.gerenciamento_doacoes.Entity.DoacaoEntity;
import com.gerenciadordedoacoes.gerenciamento_doacoes.Entity.DoadorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DoacaoRepository extends JpaRepository<DoacaoEntity, Long> {
    List<DoacaoEntity> findByDoador(DoadorEntity doador);
    List<DoacaoEntity> findByDoadorId(Long idDoador);
}

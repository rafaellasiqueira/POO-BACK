package com.gerenciadordedoacoes.gerenciamento_doacoes.Repository;

import com.gerenciadordedoacoes.gerenciamento_doacoes.Entity.InstituicaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface InstituicaoRepository extends JpaRepository<InstituicaoEntity, Long> {
    Optional<InstituicaoEntity> findByEmail(String email);
}

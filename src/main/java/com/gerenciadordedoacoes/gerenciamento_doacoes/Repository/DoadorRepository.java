package com.gerenciadordedoacoes.gerenciamento_doacoes.Repository;

import com.gerenciadordedoacoes.gerenciamento_doacoes.Entity.DoadorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface DoadorRepository extends JpaRepository<DoadorEntity, Long> {
    Optional<DoadorEntity> findByEmail(String email);

}

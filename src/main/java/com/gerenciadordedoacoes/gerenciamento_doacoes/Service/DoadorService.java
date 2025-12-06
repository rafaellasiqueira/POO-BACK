package com.gerenciadordedoacoes.gerenciamento_doacoes.Service;

import com.gerenciadordedoacoes.gerenciamento_doacoes.Entity.DoadorEntity;
import com.gerenciadordedoacoes.gerenciamento_doacoes.Repository.DoadorRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class DoadorService {

    private final DoadorRepository doadorRepository;

    public DoadorService(DoadorRepository doadorRepository) {
        this.doadorRepository = doadorRepository;
    }

    public DoadorEntity cadastrar(DoadorEntity doador) {
        return doadorRepository.save(doador);
    }

    public Optional<DoadorEntity> login(String email, String senha) {
        return doadorRepository.findByEmail(email)
                .filter(d -> d.getSenha().equals(senha));
    }

    public DoadorEntity atualizar(DoadorEntity doador) {
        return doadorRepository.save(doador);
    }
}

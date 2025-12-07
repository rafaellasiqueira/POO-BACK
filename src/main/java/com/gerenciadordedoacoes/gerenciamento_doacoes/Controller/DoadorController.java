package com.gerenciadordedoacoes.gerenciamento_doacoes.Controller;

import com.gerenciadordedoacoes.gerenciamento_doacoes.Entity.DoadorEntity;
import com.gerenciadordedoacoes.gerenciamento_doacoes.Service.DoadorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/doador")
public class DoadorController {

    private final DoadorService doadorService;

    public DoadorController(DoadorService doadorService) {
        this.doadorService = doadorService;
    }

    @PostMapping("/add")
    public ResponseEntity<DoadorEntity> cadastrar(@RequestBody DoadorEntity doador) {
        return ResponseEntity.ok(doadorService.cadastrar(doador));
    }

    @PostMapping("/login")
    public ResponseEntity<DoadorEntity> login(@RequestBody DoadorEntity doador) {
        Optional<DoadorEntity> usuario = doadorService.login(doador.getEmail(), doador.getSenha());
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(401).build());
    }

    @PutMapping("/update")
    public ResponseEntity<DoadorEntity> atualizar(@RequestBody DoadorEntity doador) {
        return ResponseEntity.ok(doadorService.atualizar(doador));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> excluirConta(@PathVariable Long id) {
        Optional<DoadorEntity> doador = doadorService.buscarPorId(id);
        if (doador.isPresent()) {
            doadorService.excluir(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

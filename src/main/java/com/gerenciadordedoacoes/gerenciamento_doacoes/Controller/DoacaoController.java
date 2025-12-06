package com.gerenciadordedoacoes.gerenciamento_doacoes.Controller;

import com.gerenciadordedoacoes.gerenciamento_doacoes.Entity.DoacaoEntity;
import com.gerenciadordedoacoes.gerenciamento_doacoes.Entity.DoadorEntity;
import com.gerenciadordedoacoes.gerenciamento_doacoes.Service.DoacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/doacao")
public class DoacaoController {

    private final DoacaoService doacaoService;

    public DoacaoController(DoacaoService doacaoService) {
        this.doacaoService = doacaoService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<DoacaoEntity> registrar(@RequestBody DoacaoEntity doacao) {
        return ResponseEntity.ok(doacaoService.registrarDoacao(doacao));
    }

    @PostMapping("/listDoador")
    public ResponseEntity<List<DoacaoEntity>> listarPorDoador(@RequestBody DoadorEntity doador) {
        return ResponseEntity.ok(doacaoService.listarDoacoesPorDoador(doador));
    }

    @GetMapping("/historico/{idDoador}")
    public ResponseEntity<List<DoacaoEntity>> listarPorDoador(@PathVariable Long idDoador) {
        List<DoacaoEntity> doacoes = doacaoService.listarDoacoesPorIdDoador(idDoador);
        return ResponseEntity.ok(doacoes);
    }

    // Método DELETE para excluir uma doação
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        boolean excluido = doacaoService.excluirDoacao(id);
        if (excluido) {
            return ResponseEntity.noContent().build();  // 204 No Content
        } else {
            return ResponseEntity.notFound().build();  // 404 Not Found, caso não encontre a doação
        }
    }
}
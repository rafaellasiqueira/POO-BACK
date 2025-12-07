package com.gerenciadordedoacoes.gerenciamento_doacoes.Controller;

import com.gerenciadordedoacoes.gerenciamento_doacoes.Entity.PedidoEntity;
import com.gerenciadordedoacoes.gerenciamento_doacoes.Entity.InstituicaoEntity;
import com.gerenciadordedoacoes.gerenciamento_doacoes.Service.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping("/add")
    public ResponseEntity<PedidoEntity> criarPedido(@RequestBody PedidoEntity pedido) {
        return ResponseEntity.ok(pedidoService.criarPedido(pedido));
    }

    @GetMapping("/listall")
    public ResponseEntity<List<PedidoEntity>> listarPedidos() {
        return ResponseEntity.ok(pedidoService.listarPedidos());
    }

    @PostMapping("/listInstituicao")
    public ResponseEntity<List<PedidoEntity>> listarPorInstituicao(@RequestBody InstituicaoEntity instituicao) {
        return ResponseEntity.ok(pedidoService.listarPedidosPorInstituicao(instituicao));
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<PedidoEntity> getPedidoById(@PathVariable Long id) {
        PedidoEntity pedido = pedidoService.buscarPorId(id);
        if (pedido != null) {
            return ResponseEntity.ok(pedido);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

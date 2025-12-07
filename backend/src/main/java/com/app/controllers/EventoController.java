package com.app.controllers;

import com.app.dto.EventoDTO;
import com.app.services.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/rest/eventos")
public class EventoController {
    
    @Autowired
    private EventoService eventoService;
    
    @GetMapping
    public ResponseEntity<List<EventoDTO>> listar() {
        return ResponseEntity.ok(eventoService.listarTodos());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<EventoDTO> obter(@PathVariable Long id) {
        return ResponseEntity.ok(eventoService.obterPorId(id));
    }
    
    @PostMapping
    public ResponseEntity<EventoDTO> criar(@RequestBody EventoDTO dto) {
        return ResponseEntity.ok(eventoService.criar(dto));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<EventoDTO> atualizar(@PathVariable Long id, @RequestBody EventoDTO dto) {
        return ResponseEntity.ok(eventoService.atualizar(id, dto));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        eventoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

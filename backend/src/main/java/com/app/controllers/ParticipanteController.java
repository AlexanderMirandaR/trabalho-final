package com.app.controllers;

import com.app.dto.ParticipanteDTO;
import com.app.services.ParticipanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/rest/participantes")
public class ParticipanteController {
    
    @Autowired
    private ParticipanteService participanteService;
    
    @GetMapping("/evento/{eventoId}")
    public ResponseEntity<List<ParticipanteDTO>> listarPorEvento(@PathVariable Long eventoId) {
        return ResponseEntity.ok(participanteService.listarPorEvento(eventoId));
    }
    
    @PostMapping
    public ResponseEntity<ParticipanteDTO> criar(@RequestBody ParticipanteDTO dto) {
        return ResponseEntity.ok(participanteService.criar(dto));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ParticipanteDTO> atualizar(@PathVariable Long id, @RequestBody ParticipanteDTO dto) {
        return ResponseEntity.ok(participanteService.atualizar(id, dto));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        participanteService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

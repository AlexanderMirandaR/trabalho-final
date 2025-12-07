package com.app.services;

import com.app.dto.ParticipanteDTO;
import com.app.models.Participante;
import com.app.models.Evento;
import com.app.repositories.ParticipanteRepository;
import com.app.repositories.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParticipanteService {
    
    @Autowired
    private ParticipanteRepository participanteRepository;
    
    @Autowired
    private EventoRepository eventoRepository;
    
    public List<ParticipanteDTO> listarPorEvento(Long eventoId) {
        return participanteRepository.findByEventoId(eventoId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public ParticipanteDTO criar(ParticipanteDTO dto) {
        Evento evento = eventoRepository.findById(dto.getEventoId())
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));
        
        Participante participante = new Participante();
        participante.setNome(dto.getNome());
        participante.setEmail(dto.getEmail());
        participante.setTelefone(dto.getTelefone());
        participante.setEvento(evento);
        
        Participante salvo = participanteRepository.save(participante);
        return convertToDTO(salvo);
    }
    
    public ParticipanteDTO atualizar(Long id, ParticipanteDTO dto) {
        Participante participante = participanteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Participante não encontrado"));
        
        participante.setNome(dto.getNome());
        participante.setEmail(dto.getEmail());
        participante.setTelefone(dto.getTelefone());
        
        Participante atualizado = participanteRepository.save(participante);
        return convertToDTO(atualizado);
    }
    
    public void deletar(Long id) {
        participanteRepository.deleteById(id);
    }
    
    private ParticipanteDTO convertToDTO(Participante participante) {
        return new ParticipanteDTO(
                participante.getId(),
                participante.getNome(),
                participante.getEmail(),
                participante.getTelefone(),
                participante.getEvento().getId()
        );
    }
}

package com.app.services;

import com.app.dto.EventoDTO;
import com.app.models.Evento;
import com.app.repositories.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventoService {
    
    @Autowired
    private EventoRepository eventoRepository;
    
    public List<EventoDTO> listarTodos() {
        return eventoRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public EventoDTO obterPorId(Long id) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));
        return convertToDTO(evento);
    }
    
    public EventoDTO criar(EventoDTO dto) {
        Evento evento = new Evento();
        evento.setTitulo(dto.getTitulo());
        evento.setDescricao(dto.getDescricao());
        evento.setDataInicio(dto.getDataInicio());
        evento.setDataFim(dto.getDataFim());
        evento.setLocal(dto.getLocal());
        evento.setCapacidade(dto.getCapacidade());
        
        Evento salvo = eventoRepository.save(evento);
        return convertToDTO(salvo);
    }
    
    public EventoDTO atualizar(Long id, EventoDTO dto) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));
        
        evento.setTitulo(dto.getTitulo());
        evento.setDescricao(dto.getDescricao());
        evento.setDataInicio(dto.getDataInicio());
        evento.setDataFim(dto.getDataFim());
        evento.setLocal(dto.getLocal());
        evento.setCapacidade(dto.getCapacidade());
        
        Evento atualizado = eventoRepository.save(evento);
        return convertToDTO(atualizado);
    }
    
    public void deletar(Long id) {
        eventoRepository.deleteById(id);
    }
    
    private EventoDTO convertToDTO(Evento evento) {
        return new EventoDTO(
                evento.getId(),
                evento.getTitulo(),
                evento.getDescricao(),
                evento.getDataInicio(),
                evento.getDataFim(),
                evento.getLocal(),
                evento.getCapacidade()
        );
    }
}

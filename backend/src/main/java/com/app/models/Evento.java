package com.app.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "eventos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String titulo;
    
    @Column(nullable = false, columnDefinition = "TEXT")
    private String descricao;
    
    @Column(nullable = false)
    private LocalDateTime dataInicio;
    
    @Column(nullable = false)
    private LocalDateTime dataFim;
    
    @Column(nullable = false)
    private String local;
    
    @Column(nullable = false)
    private Integer capacidade;
    
    @OneToMany(mappedBy = "evento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Participante> participantes;
    
    @PrePersist
    protected void onCreate() {
        if (dataInicio == null) {
            dataInicio = LocalDateTime.now();
        }
        if (dataFim == null) {
            dataFim = LocalDateTime.now().plusHours(1);
        }
    }
}

package com.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParticipanteDTO {
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private Long eventoId;
}

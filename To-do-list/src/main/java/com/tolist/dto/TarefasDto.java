package com.tolist.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tolist.enums.Prioridade;
import com.tolist.enums.Status;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TarefasDto {

    @NotBlank(message = "O nome não pode estar vazio")
    private String nome;

    @NotBlank(message = "A descrição não pode estar vazia")
    private String descricao;

    private Status status;
    private Prioridade prioridade;

    private LocalDate dataCriacao;
    private LocalDate dataVencimento;
}


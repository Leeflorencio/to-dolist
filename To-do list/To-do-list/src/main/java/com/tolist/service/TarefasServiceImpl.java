package com.tolist.service;

import com.tolist.dto.TarefasDto;
import com.tolist.model.TarefasModel;
import com.tolist.repository.TarefasRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class TarefasServiceImpl implements TarefasService {

    @Autowired
    TarefasRepository repository;

    @Override
    public ResponseEntity<Object> cadastrar(TarefasDto tarefas) {

        //alterar a verificação para id que se relaciona com o nome da tarefa
        if (repository.existsByNome(tarefas.getNome())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("A tarefa já está cadastrada");
        } else if (tarefas.getNome().isBlank() && tarefas.getDescricao().isBlank()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("O nome e descrição da tarefa não podem estar vazios");
        }

        var tarefasModel = new TarefasModel();
        BeanUtils.copyProperties(tarefas, tarefasModel);
        tarefasModel.setDataCriacao(LocalDateTime.now(ZoneId.of("UTC")));

        repository.save(tarefasModel);
        return ResponseEntity.status(HttpStatus.CREATED).body("Tarefa cadastrada com sucesso");
    }
}

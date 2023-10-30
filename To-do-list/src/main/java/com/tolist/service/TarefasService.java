package com.tolist.service;

import com.tolist.dto.TarefasDto;
import com.tolist.model.TarefasModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface TarefasService {
    ResponseEntity<Object> cadastrar(TarefasDto tarefas);

    ResponseEntity<Page<TarefasModel>> listarTodos(Pageable pageable);

    ResponseEntity<Object> buscarTarefa(Long id);

    ResponseEntity<Object> deletar(Long id);

    ResponseEntity<Object> atualizar(Long id, TarefasDto tarefas);
}

package com.tolist.service;

import com.tolist.dto.TarefasDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface TarefasService {
    ResponseEntity<Object> cadastrar(TarefasDto tarefas);
}

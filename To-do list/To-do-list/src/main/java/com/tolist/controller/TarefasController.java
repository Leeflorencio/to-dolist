package com.tolist.controller;

import com.tolist.dto.TarefasDto;
import com.tolist.service.TarefasService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tarefas")
public class TarefasController {

    @Autowired
    TarefasService tarefasService;


    @PostMapping("/cadastrar")
    public ResponseEntity<Object> cadastrar(@RequestBody @Valid TarefasDto tarefas){
        return tarefasService.cadastrar(tarefas);
    }
}

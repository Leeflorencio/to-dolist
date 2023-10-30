package com.tolist.controller;

import com.tolist.dto.TarefasDto;
import com.tolist.model.TarefasModel;
import com.tolist.service.TarefasService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @GetMapping("/listar")
    public ResponseEntity<Page<TarefasModel>> listarTodos(Integer paginas, Integer registros){
        Pageable pageable = PageRequest.of(paginas - 1, registros);
        return tarefasService.listarTodos(pageable);
    }

    @GetMapping("/listarPorId{id}")
    public ResponseEntity<Object> buscarTarefa(@PathVariable Long id){
        return tarefasService.buscarTarefa(id);
    }

    @DeleteMapping("/deletar{id}")
    public ResponseEntity<Object> deletar(@PathVariable Long id){
        return tarefasService.deletar(id);
    }

    @PutMapping("/atualizar{id}")
    public ResponseEntity<Object> atualizar(@PathVariable Long id, @RequestBody TarefasDto tarefas){
        return tarefasService.atualizar(id,tarefas);
    }
}

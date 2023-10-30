package com.tolist.service;

import com.tolist.dto.TarefasDto;
import com.tolist.enums.Status;
import com.tolist.model.TarefasModel;
import com.tolist.repository.TarefasRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class TarefasServiceImpl implements TarefasService {

    @Autowired
    TarefasRepository repository;

    @Override
    public ResponseEntity<Object> cadastrar(TarefasDto tarefas) {

        try {
            //alterar a verificação para id que se relaciona com o nome da tarefa
            if (repository.existsByNome(tarefas.getNome())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("A tarefa já está cadastrada");
            } else if (tarefas.getNome().isBlank() && tarefas.getDescricao().isBlank()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("O nome e descrição da tarefa não podem estar vazios");
            }

            var tarefasModel = new TarefasModel();
            BeanUtils.copyProperties(tarefas, tarefasModel);
            tarefasModel.setDataCriacao(LocalDate.now());
            //(LocalDate.now(ZoneId.of("UTC")));
            repository.save(tarefasModel);
            return ResponseEntity.status(HttpStatus.CREATED).body("Tarefa cadastrada com sucesso");
        } catch (BeansException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseEntity<Page<TarefasModel>> listarTodos(Pageable pageable) {
        try {
            Page<TarefasModel> tarefas = repository.findAll(pageable);
            return ResponseEntity.status(HttpStatus.CREATED).body(tarefas);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseEntity<Object> buscarTarefa(Long id) {
        try {
            if (repository.existsById(id)) {
                Optional<TarefasModel> tarefa = repository.findById(id);
                return ResponseEntity.status(HttpStatus.OK).body(tarefa);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa não localizada, informe um identificador válido");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseEntity<Object> deletar(Long id) {
        try {
            if (repository.existsById(id)) {
                repository.deleteById(id);
                return ResponseEntity.status(HttpStatus.OK).body("Tarefa excluída com sucesso");
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa não localizada, informe um identificador válido");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseEntity<Object> atualizar(Long id, TarefasDto tarefas) {
        try {
            if (repository.existsById(id)) {
                    var tarefasModel = new TarefasModel();
                    BeanUtils.copyProperties(tarefas, tarefasModel);

                    tarefasModel.setNome(tarefas.getNome());
                    tarefasModel.setDescricao(tarefas.getDescricao());
                    tarefasModel.setStatus(tarefas.getStatus());
                    tarefasModel.setPrioridade(tarefas.getPrioridade());
                    tarefasModel.setDataVencimento(tarefas.getDataVencimento());

                    repository.save(tarefasModel);

                    return ResponseEntity.status(HttpStatus.CREATED).body("Tarefa atualizada com sucesso");
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Dados incorretoss");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}

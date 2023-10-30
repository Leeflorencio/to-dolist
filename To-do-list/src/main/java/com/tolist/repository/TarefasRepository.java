package com.tolist.repository;

import com.tolist.model.TarefasModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TarefasRepository extends JpaRepository<TarefasModel,Long> {

    boolean existsByNome(String nome);

}

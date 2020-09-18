package br.com.totvs.repository;

import br.com.totvs.entity.Pessoa;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoaRepository extends CrudRepository<Pessoa, Integer> {

    @Query("select p from Pessoa p ORDER BY p.id DESC")
    List<Pessoa> listar(Pageable pageable);

}

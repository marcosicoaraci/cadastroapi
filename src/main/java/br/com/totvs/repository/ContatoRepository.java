package br.com.totvs.repository;

import br.com.totvs.entity.Contato;
import br.com.totvs.entity.Pessoa;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContatoRepository extends CrudRepository<Contato, Integer> {

    @Query("select c from Contato c WHERE c.pessoaContatoId.id = :idPessoa")
    List<Contato> listaPorIdPessoa(@Param("idPessoa") Integer idPessoa);

    @Query("select c from Contato c ORDER BY c.id DESC")
    List<Contato> listarTodos(Pageable pageable);


}

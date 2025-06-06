package br.com.totvs.repository;

import br.com.totvs.entity.Contato;
import br.com.totvs.entity.Dependente;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DependenteRepository extends CrudRepository<Dependente, Integer> {

    @Query("select d from Dependente d WHERE d.pessoaDependenteId.id = :idPessoa")
    List<Dependente> listaPorIdPessoa(@Param("idPessoa") Integer idPessoa);

    @Query("select d from Dependente d ORDER BY d.id DESC")
    List<Dependente> listarTodos(Pageable pageable);

}

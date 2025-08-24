package br.com.mrchagas.repository;

import br.com.mrchagas.entity.Endereco;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnderecoRepository extends CrudRepository<Endereco, Integer> {

    @Query("select e from Endereco e WHERE e.pessoaEnderecoId.id = :idPessoa")
    List<Endereco> listaPorIdPessoa(@Param("idPessoa") Integer idPessoa);

    @Query("select e from Endereco e ORDER BY e.id DESC")
    List<Endereco> listarTodos(Pageable pageable);

}

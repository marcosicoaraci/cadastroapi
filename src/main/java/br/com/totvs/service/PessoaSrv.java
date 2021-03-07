package br.com.totvs.service;

import br.com.totvs.entity.Pessoa;
import br.com.totvs.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaSrv {

    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa getUm(Integer id){
        try {
            return pessoaRepository.findById(id).get();
        }catch (Exception e){
            return null;
        }
    }

    public List<Pessoa> listarTodos(Pageable pageable){
        return pessoaRepository.listar(pageable);
    }

    public void salvar(Pessoa pessoa){
        try {
            pessoaRepository.save(pessoa);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void excluir (Integer id){
        try {
            pessoaRepository.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
        }
    }



}

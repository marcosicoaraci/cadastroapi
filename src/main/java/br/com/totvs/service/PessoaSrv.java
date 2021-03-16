package br.com.totvs.service;

import br.com.totvs.entity.Pessoa;
import br.com.totvs.exceptions.ObjetoNaoEncontradoException;
import br.com.totvs.exceptions.ObjetoNotContentException;
import br.com.totvs.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PessoaSrv {

    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa getUm(Integer id){
        return this.pessoaRepository.findById(id)
                .orElseThrow(() -> new ObjetoNaoEncontradoException("Objeto com id "+id+" não encontrado"));
    }

    public List<Pessoa> listarTodos(Pageable pageable){
        List<Pessoa> resposta = pessoaRepository.listar(pageable);
        if (resposta.size() == 0) {
            throw new ObjetoNotContentException("A lista não possui itens");
        }

        return resposta;
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

package br.com.totvs.service;

import br.com.totvs.entity.Endereco;
import br.com.totvs.entity.Pessoa;
import br.com.totvs.exceptions.ObjetoNaoEncontradoException;
import br.com.totvs.repository.EnderecoRepository;
import br.com.totvs.repository.PessoaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoSrv {

    private final EnderecoRepository enderecoRepository;
    private final PessoaRepository pessoaRepository;

    public EnderecoSrv(EnderecoRepository enderecoRepository, PessoaRepository pessoaRepository) {
        this.enderecoRepository = enderecoRepository;
        this.pessoaRepository = pessoaRepository;
    }

    public Endereco getUm(Integer id){
        try {
            return enderecoRepository.findById(id).get();
        }catch (Exception e){
            throw new ObjetoNaoEncontradoException("Erro ao recuperar endereço "+e);
        }
    }

    public List<Endereco> listarPorIdPessoa(Integer id){
        return enderecoRepository.listaPorIdPessoa(id);
    }

    public void salvar(Endereco endereco,Integer idPessoa){
        try {
            Pessoa pessoa = pessoaRepository.findById(idPessoa).get();
            endereco.setPessoaEnderecoId(pessoa);
            enderecoRepository.save(endereco);
        }catch (Exception e){
            throw new ObjetoNaoEncontradoException("Erro ao cadastrar endereco "+e);
        }
    }

    public void excluir (Integer idPessoa){
        try {
            enderecoRepository.deleteById(idPessoa);
        }catch (Exception e){
            throw new ObjetoNaoEncontradoException("Erro ao excluir endereco "+e);
        }
    }

}

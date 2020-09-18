package br.com.totvs.service;

import br.com.totvs.entity.Endereco;
import br.com.totvs.entity.Pessoa;
import br.com.totvs.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoSrv {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private PessoaSrv pessoaSrv;

    public Endereco getUm(Integer id){
        try {
            return enderecoRepository.findById(id).get();
        }catch (Exception e){
            return null;
        }
    }

    public List<Endereco> listarPorIdPessoa(Integer id){
        return enderecoRepository.listaPorIdPessoa(id);
    }

    public void salvar(Endereco endereco,Integer idPessoa){
        try {
            Pessoa pessoa = pessoaSrv.getUm(idPessoa);
            endereco.setPessoaEnderecoId(pessoa);
            enderecoRepository.save(endereco);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void excluir (Integer idPessoa){
        try {
            enderecoRepository.deleteById(idPessoa);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

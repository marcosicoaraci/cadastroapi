package br.com.totvs.service;

import br.com.totvs.entity.Contato;
import br.com.totvs.entity.Pessoa;
import br.com.totvs.repository.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContatoSrv {

    @Autowired
    private ContatoRepository contatoRepository;

    @Autowired
    private PessoaSrv pessoaSrv;

    public Contato getUm(Integer id){
        try {
            return contatoRepository.findById(id).get();
        }catch (Exception e){
            return null;
        }
    }

    public List<Contato> listarPorIdPessoa(Integer id){
        return contatoRepository.listaPorIdPessoa(id);
    }

    public void salvar(Contato contato, Integer idPessoa){
        try {
            Pessoa pessoa = pessoaSrv.getUm(idPessoa);
            contato.setPessoaContatoId(pessoa);
            contatoRepository.save(contato);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void excluir (Integer id){
        try {
            contatoRepository.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

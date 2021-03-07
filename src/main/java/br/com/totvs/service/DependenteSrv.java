package br.com.totvs.service;

import br.com.totvs.entity.Dependente;
import br.com.totvs.entity.Pessoa;
import br.com.totvs.repository.DependenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DependenteSrv {

    @Autowired
    private DependenteRepository dependenteRepository;

    @Autowired
    private PessoaSrv pessoaSrv;

    public Dependente getUm(Integer id){
        try {
            return dependenteRepository.findById(id).get();
        }catch (Exception e){
            return null;
        }
    }

    public List<Dependente> listarPorIdPessoa(Integer id){
        return dependenteRepository.listaPorIdPessoa(id);
    }


    public void salvar(Dependente dependente, Integer idPessoa){
        try {
            Pessoa pessoa = pessoaSrv.getUm(idPessoa);
            dependente.setPessoaDependenteId(pessoa);
            dependenteRepository.save(dependente);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void excluir (Integer id){
        try {
            dependenteRepository.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

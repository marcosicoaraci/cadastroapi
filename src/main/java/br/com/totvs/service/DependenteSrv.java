package br.com.totvs.service;

import br.com.totvs.dto.request.DependenteRequestDTO;
import br.com.totvs.dto.response.DependenteResponseDTO;
import br.com.totvs.entity.Dependente;
import br.com.totvs.exceptions.ObjetoNaoEncontradoException;
import br.com.totvs.repository.DependenteRepository;
import br.com.totvs.repository.PessoaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DependenteSrv {

    private DependenteRepository dependenteRepository;
    private PessoaRepository pessoaRepository;

    public DependenteSrv(DependenteRepository dependenteRepository, PessoaRepository pessoaRepository) {
        this.dependenteRepository = dependenteRepository;
        this.pessoaRepository = pessoaRepository;
    }
    private ModelMapper modelMapper = new ModelMapper();

    public DependenteResponseDTO getUm(Integer id){
        try {
            var dependente = dependenteRepository.findById(id).get();
            return modelMapper.map(dependente, DependenteResponseDTO.class);
        }catch (Exception e){
            throw new ObjetoNaoEncontradoException("Erro ao recuperar dependente "+e);
        }
    }

    public List<Dependente> listarPorIdPessoa(Integer id){
        return dependenteRepository.listaPorIdPessoa(id);
    }


    public DependenteResponseDTO salvar(DependenteRequestDTO dependenteRequestDTO){
        try {
            Dependente dependente = modelMapper.map(dependenteRequestDTO, Dependente.class);
            dependenteRepository.save(dependente);
            return modelMapper.map(dependente, DependenteResponseDTO.class);
        }catch (Exception e){
            throw new ObjetoNaoEncontradoException("Erro ao salvar dependente "+e);
        }
    }
    public DependenteResponseDTO atualizar(Integer idDependente,DependenteRequestDTO dependenteRequestDTO){
        try {
            Dependente dependente = modelMapper.map(dependenteRequestDTO, Dependente.class);
            dependente.setId(idDependente);
            dependenteRepository.save(dependente);
            return modelMapper.map(dependente, DependenteResponseDTO.class);
        }catch (Exception e){
            throw new ObjetoNaoEncontradoException("Erro ao salvar dependente "+e);
        }
    }

    public void excluir (Integer id){
        try {
            dependenteRepository.deleteById(id);
        }catch (Exception e){
            throw new ObjetoNaoEncontradoException("Erro ao excluir dependente "+e);
        }
    }

}

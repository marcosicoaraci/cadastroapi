package br.com.mrchagas.service;

import br.com.mrchagas.dto.request.DependenteRequestDTO;
import br.com.mrchagas.dto.response.DependenteResponseDTO;
import br.com.mrchagas.entity.Dependente;
import br.com.mrchagas.exceptions.ObjetoNaoEncontradoException;
import br.com.mrchagas.interfaces.IDependenteService;
import br.com.mrchagas.repository.DependenteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DependenteSrvImpl implements IDependenteService {

    private DependenteRepository dependenteRepository;

    public DependenteSrvImpl(DependenteRepository dependenteRepository) {
        this.dependenteRepository = dependenteRepository;
    }
    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public DependenteResponseDTO getUm(Integer id) {
        try {
            var dependente = dependenteRepository.findById(id).get();
            return modelMapper.map(dependente, DependenteResponseDTO.class);
        }catch (Exception e){
            throw new ObjetoNaoEncontradoException("Erro ao recuperar dependente "+e);
        }
    }

    @Override
    public List<DependenteResponseDTO> listarTodos(Pageable pageable) {
        List<DependenteResponseDTO> responseDTOList = new ArrayList<>();
        var resposta = dependenteRepository.listarTodos(pageable);
        if (resposta.isEmpty()) {
            throw new ObjetoNaoEncontradoException("A lista não possui itens");
        }else{
            for (Dependente dependente : resposta) {
                DependenteResponseDTO dependenteResponse = modelMapper.map(dependente, DependenteResponseDTO.class);
                responseDTOList.add(dependenteResponse);
            }
        }
        return responseDTOList;
    }

    @Override
    public DependenteResponseDTO salvar(DependenteRequestDTO requestDTO) {
        return saveOrUpdate(requestDTO);
    }

    @Override
    public DependenteResponseDTO atualizar(DependenteRequestDTO requestDTO) {
        return saveOrUpdate(requestDTO);
    }

    @Override
    public void excluir(Integer id) {
        try {
            dependenteRepository.deleteById(id);
        }catch (Exception e){
            throw new ObjetoNaoEncontradoException("Erro ao excluir dependente "+e);
        }
    }

    @Override
    public List<DependenteResponseDTO> listarPorIdPessoa(Integer id) {
        List<DependenteResponseDTO> responseDTOList = new ArrayList<>();
        var resposta = dependenteRepository.listaPorIdPessoa(id);
        if (resposta.isEmpty()) {
            throw new ObjetoNaoEncontradoException("A lista não possui itens");
        }else{
            for (Dependente dependente : resposta) {
                DependenteResponseDTO dependenteResponse = modelMapper.map(dependente, DependenteResponseDTO.class);
                responseDTOList.add(dependenteResponse);
            }
        }
        return responseDTOList;

    }

    private DependenteResponseDTO saveOrUpdate(DependenteRequestDTO requestDTO){
        try {
            var dependente = modelMapper.map(requestDTO, Dependente.class);
            dependenteRepository.save(dependente);
            return modelMapper.map(dependente, DependenteResponseDTO.class);
        }catch (Exception e){
            throw new ObjetoNaoEncontradoException("Erro ao cadastrar objeto "+e);
        }

    }
}

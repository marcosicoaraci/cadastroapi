package br.com.totvs.service;

import br.com.totvs.dto.request.EnderecoRequestDTO;
import br.com.totvs.dto.response.EnderecoResponseDTO;
import br.com.totvs.entity.Endereco;
import br.com.totvs.exceptions.ObjetoNaoEncontradoException;
import br.com.totvs.interfaces.IEnderecoService;
import br.com.totvs.repository.EnderecoRepository;
import br.com.totvs.repository.PessoaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EnderecoSrvImpl implements IEnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    public EnderecoSrvImpl(EnderecoRepository enderecoRepository, PessoaRepository pessoaRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    @Override
    public List<EnderecoResponseDTO> listarPorIdPessoa(Integer id) {
        List<EnderecoResponseDTO> responseDTOList = new ArrayList<>();
        var resposta = enderecoRepository.listaPorIdPessoa(id);
        if (resposta.isEmpty()) {
            throw new ObjetoNaoEncontradoException("A lista não possui itens");
        }else{
            for (Endereco endereco : resposta) {
                var contatoResponse = modelMapper.map(endereco, EnderecoResponseDTO.class);
                responseDTOList.add(contatoResponse);
            }
        }
        return responseDTOList;

    }

    @Override
    public EnderecoResponseDTO getUm(Integer id) {
        try {
            Endereco endereco = enderecoRepository.findById(id).get();
            return modelMapper.map(endereco, EnderecoResponseDTO.class);
        }catch (Exception e){
            throw new ObjetoNaoEncontradoException("Erro ao recuperar endereço "+e);
        }
    }

    @Override
    public List<EnderecoResponseDTO> listarTodos(Pageable pageable) {
        List<EnderecoResponseDTO> responseDTOList = new ArrayList<>();
        var resposta = enderecoRepository.listarTodos(pageable);
        if (resposta.isEmpty()) {
            throw new ObjetoNaoEncontradoException("A lista não possui itens");
        }else{
            for (Endereco endereco : resposta) {
                var contatoResponse = modelMapper.map(endereco, EnderecoResponseDTO.class);
                responseDTOList.add(contatoResponse);
            }
        }
        return responseDTOList;

    }

    @Override
    public EnderecoResponseDTO salvar(EnderecoRequestDTO requestDTO) {
        return saveOrUpdate(requestDTO);
    }

    @Override
    public EnderecoResponseDTO atualizar(EnderecoRequestDTO requestDTO) {
        return saveOrUpdate(requestDTO);
    }

    @Override
    public void excluir(Integer id) {
        try {
            enderecoRepository.deleteById(id);
        }catch (Exception e){
            throw new ObjetoNaoEncontradoException("Erro ao excluir endereco "+e);
        }
    }

    private EnderecoResponseDTO saveOrUpdate(EnderecoRequestDTO requestDTO){
        try {
            var endereco = modelMapper.map(requestDTO, Endereco.class);
            enderecoRepository.save(endereco);
            return modelMapper.map(endereco, EnderecoResponseDTO.class);
        }catch (Exception e){
            throw new ObjetoNaoEncontradoException("Erro ao cadastrar objeto "+e);
        }

    }

}

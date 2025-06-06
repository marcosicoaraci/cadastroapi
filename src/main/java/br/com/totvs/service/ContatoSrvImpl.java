package br.com.totvs.service;

import br.com.totvs.dto.request.ContatoRequestDTO;
import br.com.totvs.dto.request.PessoaRequestDTO;
import br.com.totvs.dto.response.ContatoResponseDTO;
import br.com.totvs.dto.response.PessoaResponseDTO;
import br.com.totvs.entity.Contato;
import br.com.totvs.entity.Pessoa;
import br.com.totvs.exceptions.ObjetoNaoEncontradoException;
import br.com.totvs.interfaces.IContatoService;
import br.com.totvs.repository.ContatoRepository;
import br.com.totvs.repository.PessoaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContatoSrvImpl implements IContatoService {
    private ContatoRepository contatoRepository;
    private PessoaRepository pessoaRepository;
    public ContatoSrvImpl(ContatoRepository contatoRepository, PessoaRepository pessoaRepository) {
        this.contatoRepository = contatoRepository;
        this.pessoaRepository = pessoaRepository;
    }
    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public ContatoResponseDTO getUm(Integer id) {
        try {
            var contato = contatoRepository.findById(id).get();
            var resposta = modelMapper.map(contato, ContatoResponseDTO.class);
            return resposta;
        }catch (Exception e){
            throw new ObjetoNaoEncontradoException("Erro ao recuperar contato "+e);
        }
    }

    @Override
    public List<ContatoResponseDTO> listarTodos(Pageable pageable) {
        List<ContatoResponseDTO> responseDTOList = new ArrayList<>();
        var resposta = contatoRepository.listarTodos(pageable);
        if (resposta.isEmpty()) {
            throw new ObjetoNaoEncontradoException("A lista não possui itens");
        }else{
            for (Contato contato : resposta) {
                ContatoResponseDTO contatoResponse = modelMapper.map(contato, ContatoResponseDTO.class);
                responseDTOList.add(contatoResponse);
            }
        }

        return responseDTOList;

    }

    @Override
    public ContatoResponseDTO salvar(ContatoRequestDTO requestDTO) {
       return saveOrUpdate(requestDTO);
    }

    @Override
    public ContatoResponseDTO atualizar(ContatoRequestDTO requestDTO) {
        return saveOrUpdate(requestDTO);
    }

    @Override
    public void excluir(Integer id) {
        try {
            contatoRepository.deleteById(id);
        }catch (Exception e){
            throw new ObjetoNaoEncontradoException("Erro ao Excluir objeto "+e);
        }
    }

    @Override
    public List<ContatoResponseDTO> listarPorIdPessoa(Integer id) {
        List<ContatoResponseDTO> responseDTOList = new ArrayList<>();
        List<Contato> resposta = contatoRepository.listaPorIdPessoa(id);
        if (resposta.isEmpty()) {
            throw new ObjetoNaoEncontradoException("A lista não possui itens");
        }else{
            for (Contato contato : resposta) {
                ContatoResponseDTO contatoResponse = modelMapper.map(contato, ContatoResponseDTO.class);
                responseDTOList.add(contatoResponse);
            }
        }
        return responseDTOList;
    }

    private ContatoResponseDTO saveOrUpdate(ContatoRequestDTO pessoaRequestDTO){
        try {
            var contato = modelMapper.map(pessoaRequestDTO, Contato.class);
            contatoRepository.save(contato);
            return modelMapper.map(contato, ContatoResponseDTO.class);
        }catch (Exception e){
            throw new ObjetoNaoEncontradoException("Erro ao cadastrar objeto "+e);
        }

    }


}

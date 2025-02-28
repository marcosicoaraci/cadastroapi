package br.com.totvs.service;

import br.com.totvs.dto.request.ContatoRequestDTO;
import br.com.totvs.dto.response.ContatoResponseDTO;
import br.com.totvs.dto.response.PessoaResponseDTO;
import br.com.totvs.entity.Contato;
import br.com.totvs.entity.Pessoa;
import br.com.totvs.exceptions.ObjetoNaoEncontradoException;
import br.com.totvs.repository.ContatoRepository;
import br.com.totvs.repository.PessoaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContatoSrv {
    private ContatoRepository contatoRepository;
    private PessoaRepository pessoaRepository;
    public ContatoSrv(ContatoRepository contatoRepository, PessoaRepository pessoaRepository) {
        this.contatoRepository = contatoRepository;
        this.pessoaRepository = pessoaRepository;
    }
    private ModelMapper modelMapper = new ModelMapper();

    public ContatoResponseDTO getUm(Integer id){
        try {
            Contato contato = contatoRepository.findById(id).get();
            ContatoResponseDTO resposta = modelMapper.map(contato, ContatoResponseDTO.class);
            return resposta;
        }catch (Exception e){
            throw new ObjetoNaoEncontradoException("Erro ao recuperar contato "+e);
        }
    }

    public List<ContatoResponseDTO> listarPorIdPessoa(Integer id){
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

    public ContatoResponseDTO salvar(ContatoRequestDTO contatoRequestDTO){
        try {

            Pessoa pessoa = pessoaRepository.findById(contatoRequestDTO.getPessoaContatoId()).get();
            Contato contato = modelMapper.map(contatoRequestDTO, Contato.class);
            contato.setPessoaContatoId(pessoa);
            contatoRepository.save(contato);
            ContatoResponseDTO resposta = modelMapper.map(contato, ContatoResponseDTO.class);
            return resposta;
        }catch (Exception e){
            throw new ObjetoNaoEncontradoException("Erro ao cadastrar objeto "+e);
        }
    }

    public void excluir (Integer id){
        try {
            contatoRepository.deleteById(id);
        }catch (Exception e){
            throw new ObjetoNaoEncontradoException("Erro ao Excluir objeto "+e);
        }
    }

}

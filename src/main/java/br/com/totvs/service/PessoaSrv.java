package br.com.totvs.service;

import br.com.totvs.dto.request.PessoaRequestDTO;
import br.com.totvs.dto.response.PessoaResponseDTO;
import br.com.totvs.entity.Pessoa;
import br.com.totvs.exceptions.ObjetoNaoEncontradoException;
import br.com.totvs.repository.PessoaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PessoaSrv {

    private final PessoaRepository pessoaRepository;
    private final ModelMapper modelMapper = new ModelMapper();
    public PessoaSrv(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }
    public PessoaResponseDTO getUm(Integer id){
        try {
            Pessoa pessoa = this.pessoaRepository.findById(id).get();
            return modelMapper.map(pessoa, PessoaResponseDTO.class);
        }catch (Exception e){
            throw new ObjetoNaoEncontradoException("Erro ao recuperar item "+e);
        }
    }

    public List<PessoaResponseDTO> listarTodos(Pageable pageable){
        List<PessoaResponseDTO> responseList = new ArrayList<>();
        List<Pessoa> resposta = pessoaRepository.listar(pageable);
        if (resposta.isEmpty()) {
            throw new ObjetoNaoEncontradoException("A lista não possui itens");
        }else{
            for (Pessoa pessoa : resposta) {
                PessoaResponseDTO pessoaResponse = modelMapper.map(pessoa, PessoaResponseDTO.class);
                responseList.add(pessoaResponse);
            }
        }

        return responseList;
    }

    public PessoaResponseDTO salvar(PessoaRequestDTO pessoaRequestDTO){
        try {
            Pessoa pessoa = modelMapper.map(pessoaRequestDTO, Pessoa.class);
            pessoaRepository.save(pessoa);
            PessoaResponseDTO resposta = modelMapper.map(pessoa, PessoaResponseDTO.class);
            return resposta;
        }catch (Exception e){
            throw new ObjetoNaoEncontradoException("Erro ao cadastrar objeto "+e);
        }
    }

    public void excluir (Integer id){
        try {
            pessoaRepository.deleteById(id);
        }catch (Exception e){
            throw new ObjetoNaoEncontradoException("Erro ao excluir objeto: "+e);
        }
    }



}

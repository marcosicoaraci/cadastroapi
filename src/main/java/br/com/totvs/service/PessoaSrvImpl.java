package br.com.totvs.service;

import br.com.totvs.dto.request.PessoaRequestDTO;
import br.com.totvs.dto.response.PessoaResponseDTO;
import br.com.totvs.entity.Pessoa;
import br.com.totvs.exceptions.ObjetoNaoEncontradoException;
import br.com.totvs.interfaces.IPessoaService;
import br.com.totvs.repository.PessoaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PessoaSrvImpl implements IPessoaService {

    private final PessoaRepository pessoaRepository;
    private final ModelMapper modelMapper = new ModelMapper();
    public PessoaSrvImpl(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    @Override
    public PessoaResponseDTO getUm(Integer id) {
        try {
            var pessoa = this.pessoaRepository.findById(id).get();
            return modelMapper.map(pessoa, PessoaResponseDTO.class);
        }catch (Exception e){
            throw new ObjetoNaoEncontradoException("Erro ao recuperar item "+e);
        }
    }

    @Override
    public List<PessoaResponseDTO> listarTodos(Pageable pageable) {
        List<PessoaResponseDTO> responseList = new ArrayList<>();
        var resposta = pessoaRepository.listar(pageable);
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

    @Override
    public PessoaResponseDTO salvar(PessoaRequestDTO pessoaRequestDTO) {
        return saveOrUpdate(pessoaRequestDTO);
    }

    @Override
    public PessoaResponseDTO atualizar(PessoaRequestDTO pessoaRequestDTO) {
        return saveOrUpdate(pessoaRequestDTO);
    }

    @Override
    public void excluir(Integer id) {
        try {
            pessoaRepository.deleteById(id);
        }catch (Exception e){
            throw new ObjetoNaoEncontradoException("Erro ao excluir objeto: "+e);
        }
    }

    private PessoaResponseDTO saveOrUpdate(PessoaRequestDTO pessoaRequestDTO){
        try {
            var pessoa = modelMapper.map(pessoaRequestDTO, Pessoa.class);
            pessoaRepository.save(pessoa);
            return modelMapper.map(pessoa, PessoaResponseDTO.class);
        }catch (Exception e){
            throw new ObjetoNaoEncontradoException("Erro ao cadastrar objeto "+e);
        }

    }

}

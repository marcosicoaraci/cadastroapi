package br.com.totvs.interfaces;

import br.com.totvs.dto.request.EnderecoRequestDTO;
import br.com.totvs.dto.response.EnderecoResponseDTO;

import java.util.List;

public interface IEnderecoService extends IGenericService<EnderecoRequestDTO, EnderecoResponseDTO> {

    List<EnderecoResponseDTO> listarPorIdPessoa(Integer id);
}

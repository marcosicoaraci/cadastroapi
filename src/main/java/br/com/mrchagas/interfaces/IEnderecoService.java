package br.com.mrchagas.interfaces;

import br.com.mrchagas.dto.request.EnderecoRequestDTO;
import br.com.mrchagas.dto.response.EnderecoResponseDTO;

import java.util.List;

public interface IEnderecoService extends IGenericService<EnderecoRequestDTO, EnderecoResponseDTO> {

    List<EnderecoResponseDTO> listarPorIdPessoa(Integer id);
}

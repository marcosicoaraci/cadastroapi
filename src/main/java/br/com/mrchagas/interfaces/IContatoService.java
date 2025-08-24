package br.com.mrchagas.interfaces;

import br.com.mrchagas.dto.request.ContatoRequestDTO;
import br.com.mrchagas.dto.response.ContatoResponseDTO;

import java.util.List;

public interface IContatoService extends IGenericService<ContatoRequestDTO, ContatoResponseDTO> {
    List<ContatoResponseDTO> listarPorIdPessoa(Integer id);
}

package br.com.totvs.interfaces;

import br.com.totvs.dto.request.ContatoRequestDTO;
import br.com.totvs.dto.response.ContatoResponseDTO;

import java.util.List;

public interface IContatoService extends IGenericService<ContatoRequestDTO, ContatoResponseDTO> {
    List<ContatoResponseDTO> listarPorIdPessoa(Integer id);
}

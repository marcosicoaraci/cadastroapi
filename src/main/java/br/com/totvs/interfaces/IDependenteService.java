package br.com.totvs.interfaces;

import br.com.totvs.dto.request.DependenteRequestDTO;
import br.com.totvs.dto.response.DependenteResponseDTO;

import java.util.List;

public interface IDependenteService extends IGenericService<DependenteRequestDTO, DependenteResponseDTO> {
    List<DependenteResponseDTO> listarPorIdPessoa(Integer id);
}

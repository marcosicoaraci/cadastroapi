package br.com.mrchagas.interfaces;

import br.com.mrchagas.dto.request.DependenteRequestDTO;
import br.com.mrchagas.dto.response.DependenteResponseDTO;

import java.util.List;

public interface IDependenteService extends IGenericService<DependenteRequestDTO, DependenteResponseDTO> {
    List<DependenteResponseDTO> listarPorIdPessoa(Integer id);
}

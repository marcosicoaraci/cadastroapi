package br.com.totvs.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ObjetoNaoEncontradoException extends AbstractCadastroApiExceptions {

    public ObjetoNaoEncontradoException(String message, String... args) {
        super(message, args);
    }
    public ObjetoNaoEncontradoException(String message) {
        super(message);
    }

}

package br.com.totvs.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class ObjetoNotContentException extends RuntimeException {

    public ObjetoNotContentException(String message) {
        super(message);
    }

}

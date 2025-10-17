package mediaapp.com.just4you.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RecursoNaoEncontradoExcecao extends RuntimeException{

    public RecursoNaoEncontradoExcecao(String mensagem){
        super(mensagem);
    }
}

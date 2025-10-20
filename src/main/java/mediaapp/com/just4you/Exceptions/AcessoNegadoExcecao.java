package mediaapp.com.just4you.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class AcessoNegadoExcecao extends RuntimeException {

    public AcessoNegadoExcecao(String mensagem){
        super(mensagem);
    }
}

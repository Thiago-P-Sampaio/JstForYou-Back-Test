package mediaapp.com.just4you.Exceptions;

import jakarta.servlet.http.HttpServletRequest;
import mediaapp.com.just4you.DTOs.Exception.ErroResposta;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class ManipuladorGlobalExcessoes {

    @ExceptionHandler(RecursoNaoEncontradoExcessao.class)
    public ResponseEntity<ErroResposta> recursoNaoEncontrado(RecursoNaoEncontradoExcessao exc, HttpServletRequest requisicao){
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErroResposta resposta = new ErroResposta(
                Instant.now(),
                status.value(),
                "Recurso não encontrado!",
                exc.getMessage(),
                requisicao.getRequestURI()
        );
        return ResponseEntity.status(status).body(resposta);
    }



}

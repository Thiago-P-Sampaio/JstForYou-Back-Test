package mediaapp.com.just4you.Exceptions;

import jakarta.servlet.http.HttpServletRequest;
import mediaapp.com.just4you.DTOs.Exception.ErroResposta;
import mediaapp.com.just4you.DTOs.Exception.ValidacaoErroRespota;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidacaoErroRespota> validacaoIlegal(MethodArgumentNotValidException exc, HttpServletRequest requisicao){
        HttpStatus status = HttpStatus.BAD_REQUEST;

        Map<String, String> erros = new HashMap<>();

        for (FieldError fieldError : exc.getBindingResult().getFieldErrors()){
                erros.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        ValidacaoErroRespota resposta = new ValidacaoErroRespota(
                Instant.now(),
                status.value(),
                "Erro de validação",
                erros,
                requisicao.getRequestURI()
        );

        return ResponseEntity.status(status).body(resposta);
    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErroResposta> camposInvalidos(HttpMessageNotReadableException exc, HttpServletRequest requisicao){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String erroMensagem = "O corpo da requisição é invalido ou mal formado. Verifique os tipos de dados dos campos!";
        ErroResposta resposta = new ErroResposta(
                Instant.now(),
                status.value(),
                "Requisição inconsistente!",
                erroMensagem,
                requisicao.getRequestURI()
        );

        return ResponseEntity.status(status).body(resposta);
    }



}

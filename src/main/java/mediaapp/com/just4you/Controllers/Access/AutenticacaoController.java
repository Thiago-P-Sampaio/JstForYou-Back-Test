package mediaapp.com.just4you.Controllers.Access;

import jakarta.validation.Valid;
import mediaapp.com.just4you.DTOs.Security.AuntenticacaoDTO;
import mediaapp.com.just4you.DTOs.UserAcess.CadastrarDTO;
import mediaapp.com.just4you.DTOs.UserAcess.EnviarEmailRedefinirSenha;
import mediaapp.com.just4you.DTOs.UserAcess.RedefinirSenha;
import mediaapp.com.just4you.Services.SecurityServices.AutenticacaoService;
import mediaapp.com.just4you.Services.domain.RedefinicaoSenhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")

public class AutenticacaoController {

    @Autowired
    AutenticacaoService autenticacaoService;

    @Autowired
    RedefinicaoSenhaService redefinirSenhaService;




    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuntenticacaoDTO dados ){
    return ResponseEntity.ok(autenticacaoService.login(dados));
    }

    @PostMapping("/register")
    public ResponseEntity<?> cadastrar(@RequestBody @Valid CadastrarDTO dados) {
        autenticacaoService.cadastrar(dados);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> esquecerSenha(@RequestBody @Valid EnviarEmailRedefinirSenha dados) {
        try {
            redefinirSenhaService.criarTokenRedefinicao(dados.email());
            return ResponseEntity.ok("Se um e-mail cadastrado for encontrado, um link de redefinição será enviado.");
        } catch (Exception e) {
            return ResponseEntity.ok("Se um e-mail cadastrado for encontrado, um link de redefinição será enviado.");
        }
    }


    @PostMapping("/reset-password")
    public ResponseEntity<?> redefinirSenha(@RequestBody @Valid RedefinirSenha dados){
        try{
            redefinirSenhaService.redefinirSenha(dados);
            return ResponseEntity.status(HttpStatus.CREATED).body("Senha alterada com sucesso.");
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}

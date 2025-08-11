package mediaapp.com.just4you.Controllers;

import jakarta.validation.Valid;
import mediaapp.com.just4you.DTOs.AuntenticacaoDTO;
import mediaapp.com.just4you.DTOs.CadastrarDTO;
import mediaapp.com.just4you.DTOs.RespostaLoginDTO;
import mediaapp.com.just4you.Entities.EntidadeUsuario;
import mediaapp.com.just4you.Repositories.UsuarioRepositorio;
import mediaapp.com.just4you.Roles.PermissaoUsuario;
import mediaapp.com.just4you.Services.AutenticacaoService;
import mediaapp.com.just4you.Services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/auth")

public class AutenticacaoController {

    @Autowired
    AutenticacaoService autenticacaoService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuntenticacaoDTO dados ){
    return ResponseEntity.ok(autenticacaoService.login(dados));
    }

    @PostMapping("/register")
    public ResponseEntity cadastrar(@RequestBody @Valid CadastrarDTO dados) {
        if (autenticacaoService.cadastrar(dados)) return ResponseEntity.status(HttpStatus.CREATED).body("Cadastrado com sucesso");
        else  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário não pode ser cadastrado, tente novamente!");
    }
}

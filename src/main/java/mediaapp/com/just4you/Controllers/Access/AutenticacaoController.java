package mediaapp.com.just4you.Controllers.Access;

import jakarta.validation.Valid;
import mediaapp.com.just4you.DTOs.Security.AuntenticacaoDTO;
import mediaapp.com.just4you.DTOs.UserAcess.CadastrarDTO;
import mediaapp.com.just4you.Services.SecurityServices.AutenticacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

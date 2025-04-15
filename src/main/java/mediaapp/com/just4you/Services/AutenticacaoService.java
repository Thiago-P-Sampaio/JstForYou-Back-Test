package mediaapp.com.just4you.Services;

import jakarta.validation.Valid;
import mediaapp.com.just4you.DTOs.AuntenticacaoDTO;
import mediaapp.com.just4you.DTOs.CadastrarDTO;
import mediaapp.com.just4you.DTOs.RespostaLoginDTO;
import mediaapp.com.just4you.Entities.EntidadeUsuario;
import mediaapp.com.just4you.Repositories.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.Instant;

@Service
public class AutenticacaoService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    @Autowired
    TokenService tokenService;

    public String login(@RequestBody @Valid AuntenticacaoDTO dados ){
        var credenciaisUsuario = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());
        var auth = this.authenticationManager.authenticate(credenciaisUsuario);
        var token = tokenService.gerarToken((EntidadeUsuario) auth.getPrincipal());
        return "Chave de acesso: " + token;

    }

    public boolean cadastrar(@RequestBody @Valid CadastrarDTO dados){
        if(this.usuarioRepositorio.findByEmail(dados.email()) != null) return false;
        String senhaCriptografada = new BCryptPasswordEncoder().encode(dados.senha());
        EntidadeUsuario usuario = new EntidadeUsuario();
        usuario.setDataCadastro(Instant.now());
        usuario.setNome(dados.nome());
        usuario.setSenha(senhaCriptografada);
        usuario.setEmail(dados.email());
        usuario.setDataNascimento(dados.dataNascimento());
        usuario.setRole(dados.role());

        this.usuarioRepositorio.save(usuario);

        return true;
    }


}

package mediaapp.com.just4you.Services.domain;

import mediaapp.com.just4you.DTOs.UserAcess.EnviarEmailRedefinirSenha;
import mediaapp.com.just4you.DTOs.UserAcess.RedefinirSenha;
import mediaapp.com.just4you.Entities.EntidadeUsuario;
import mediaapp.com.just4you.Entities.RedefinirSenhaToken;
import mediaapp.com.just4you.Exceptions.RecursoNaoEncontradoExcecao;
import mediaapp.com.just4you.Repositories.RedefinirSenhaTokenRepositorio;
import mediaapp.com.just4you.Repositories.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class RedefinicaoSenhaService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private RedefinirSenhaTokenRepositorio tokenRepositorio;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

@Transactional
    public void criarTokenRedefinicao(String email){
        EntidadeUsuario usuario = usuarioRepositorio.findEntidadeUsuarioByEmail(email)
                .orElseThrow(() -> new RecursoNaoEncontradoExcecao("Email não encontrado!"));

        if(usuario != null){

            String token = UUID.randomUUID().toString();
            System.out.println("Criação do token: " + token);
            LocalDateTime exp = LocalDateTime.now().plusMinutes(10);

            RedefinirSenhaToken redefinirToken = tokenRepositorio.findByUsuario(usuario)
                            .orElse(new RedefinirSenhaToken());

            redefinirToken.setToken(token);
            redefinirToken.setExpiracao(exp);
            redefinirToken.setUsuario(usuario);

            tokenRepositorio.save(redefinirToken);

            System.out.println("SALVO: " + token);

            emailService.enviarEmail(email, token, usuario.getNome());
        }

    }


    public void redefinirSenha(RedefinirSenha dto){
        RedefinirSenhaToken redefinirToken = tokenRepositorio.findByToken(dto.token());
        if(redefinirToken == null || redefinirToken.Expirado()){
            throw new RuntimeException("Token inválido ou expirado!");
        }

        EntidadeUsuario usuario = redefinirToken.getUsuario();
        if(!dto.senha().equals(dto.confirmarSenha())){
            throw new RuntimeException("As senhas não coincidem!");
        }
        usuario.setSenha(passwordEncoder.encode(dto.senha()));
        usuarioRepositorio.save(usuario);
        tokenRepositorio.delete(redefinirToken);
    }
}

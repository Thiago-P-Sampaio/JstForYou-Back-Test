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
            System.out.println("CHEGO AQUI!");
            tokenRepositorio.deleteByUsuarioId(usuario.getUsuarioId());
            System.out.println("DELEÇÃO DE TOKEN!");
            String token = UUID.randomUUID().toString();
            System.out.println("Criação do token: " + token);
            LocalDateTime exp = LocalDateTime.now().plusMinutes(10);

            RedefinirSenhaToken redefinirToken = new RedefinirSenhaToken(token, usuario, exp);
            tokenRepositorio.save(redefinirToken);
            System.out.println("SALVO: " + token);

            emailService.enviarEmail(email, token);
        }

    }


    public void redefinirSenha(RedefinirSenha dto){
        RedefinirSenhaToken redefinirToken = tokenRepositorio.findByToken(dto.token());
        if(redefinirToken != null){
            throw new RuntimeException("Token inválido");
        }
        if(redefinirToken.Expirado()){
            throw new RuntimeException("Token expirado");
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

package mediaapp.com.just4you.Services.SecurityServices;

import jakarta.validation.Valid;
import mediaapp.com.just4you.DTOs.Security.AuntenticacaoDTO;
import mediaapp.com.just4you.DTOs.UserAcess.CadastrarDTO;
import mediaapp.com.just4you.DTOs.UserAcess.RespostaLoginDTO;
import mediaapp.com.just4you.Entities.EntidadeListaUsuario;
import mediaapp.com.just4you.Entities.EntidadeUsuario;
import mediaapp.com.just4you.Exceptions.RecursoExistenteExcecao;
import mediaapp.com.just4you.Repositories.ListaUsuarioRepositorio;
import mediaapp.com.just4you.Repositories.UsuarioRepositorio;
import mediaapp.com.just4you.Roles.PermissaoUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.Instant;
import java.util.Optional;

@Service
public class AutenticacaoService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    @Autowired
    TokenService tokenService;

    @Autowired
    ListaUsuarioRepositorio listaUsuarioRepositorio;

    public RespostaLoginDTO login(@RequestBody @Valid AuntenticacaoDTO dados ){
        var credenciaisUsuario = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());
        var auth = this.authenticationManager.authenticate(credenciaisUsuario);
        EntidadeUsuario usuario = (EntidadeUsuario) auth.getPrincipal();
        var token = tokenService.gerarToken((EntidadeUsuario) auth.getPrincipal());
        String avatarUrl = (usuario.getAvatar() != null) ? usuario.getAvatar().getUrl() : null;
        return new RespostaLoginDTO(token, usuario.getNome(), usuario.getUsuarioId(), avatarUrl );

    }

    public void cadastrar(@RequestBody @Valid CadastrarDTO dados){
        if(this.usuarioRepositorio.findByEmail(dados.email()) != null){
            throw new  RecursoExistenteExcecao("Não foi possível criar o usuário");
        }
        String senhaCriptografada = new BCryptPasswordEncoder().encode(dados.senha());
        EntidadeUsuario usuario = new EntidadeUsuario();
        usuario.setDataCadastro(Instant.now());
        usuario.setNome(dados.nome());
        usuario.setSenha(senhaCriptografada);
        usuario.setEmail(dados.email());
        usuario.setDataNascimento(dados.dataNascimento());
        usuario.setRole(PermissaoUsuario.USER);   //Alteração!

        this.usuarioRepositorio.save(usuario);


        EntidadeListaUsuario listaUsuario = new EntidadeListaUsuario();
        listaUsuario.setUsuario(usuario);
        listaUsuarioRepositorio.save(listaUsuario);


    }



}

package mediaapp.com.just4you.Services.SecurityServices;

import jakarta.validation.Valid;
import mediaapp.com.just4you.DTOs.Security.AuntenticacaoDTO;
import mediaapp.com.just4you.DTOs.UserAcess.CadastrarDTO;
import mediaapp.com.just4you.DTOs.UserAcess.RespostaLoginDTO;
import mediaapp.com.just4you.Entities.EntidadeListaUsuario;
import mediaapp.com.just4you.Entities.EntidadeUsuario;
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
        return new RespostaLoginDTO(token, usuario.getNome(), usuario.getUsuarioId() );

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
        usuario.setRole(PermissaoUsuario.USER);   //Alteração!

        this.usuarioRepositorio.save(usuario);


        EntidadeListaUsuario listaUsuario = new EntidadeListaUsuario();
        listaUsuario.setUsuario(usuario);
        listaUsuarioRepositorio.save(listaUsuario);


        return true;
    }

    @Transactional
    public boolean alterarDadosUsuario(@Valid CadastrarDTO dados, Long id) {
        Optional<EntidadeUsuario> usuarioOptional = usuarioRepositorio.findById(id);

        if (usuarioOptional.isEmpty()) {
            return false; // usuário não encontrado
        }

        EntidadeUsuario editarUsuario = usuarioOptional.get();

        // Atualiza nome se for diferente
        Optional.ofNullable(dados.nome())
                .filter(novoNome -> !novoNome.isBlank() && !novoNome.equals(editarUsuario.getNome()))
                .ifPresent(editarUsuario::setNome);

        // Atualiza email se for diferente
        Optional.ofNullable(dados.email())
                .filter(novoEmail -> !novoEmail.isBlank() && !novoEmail.equals(editarUsuario.getEmail()))
                .ifPresent(editarUsuario::setEmail);

        // Atualiza senha se for diferente
        Optional.ofNullable(dados.senha())
                .filter(novaSenha -> !novaSenha.isBlank() && !new BCryptPasswordEncoder().matches(novaSenha, editarUsuario.getSenha()))
                .ifPresent(novaSenha -> {
                    String senhaCriptografada = new BCryptPasswordEncoder().encode(novaSenha);
                    editarUsuario.setSenha(senhaCriptografada);
                });

        usuarioRepositorio.save(editarUsuario); // salva alterações
        return true;
    }



}

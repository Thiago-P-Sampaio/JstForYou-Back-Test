package mediaapp.com.just4you.Services.domain;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import mediaapp.com.just4you.DTOs.Create.EditarUsuario;
import mediaapp.com.just4you.DTOs.Response.UsuarioDTO;
import mediaapp.com.just4you.DTOs.UserAcess.CadastrarDTO;
import mediaapp.com.just4you.Entities.EntidadeAvatar;
import mediaapp.com.just4you.Entities.EntidadeUsuario;
import mediaapp.com.just4you.Repositories.AvatarRepositorio;
import mediaapp.com.just4you.Repositories.ListaUsuarioRepositorio;
import mediaapp.com.just4you.Repositories.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    @Autowired
    ListaUsuarioRepositorio listaUsuarioRepositorio;

    @Autowired
    AvatarRepositorio avatarRepositorio;

 // GET
    @Transactional(readOnly = true)
    public UsuarioDTO buscarUsuarioPorId(Long id){
        EntidadeUsuario usuario = usuarioRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));


        return new UsuarioDTO(usuario);
    }

    public void deletarUsuario(Long id){
        EntidadeUsuario usuario = usuarioRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID")); // EXCEÇÃO!


        usuarioRepositorio.delete(usuario);

    }

    @Transactional
    public UsuarioDTO alterarDadosUsuario(EditarUsuario dados, Long id) {

        EntidadeUsuario editarUsuario = usuarioRepositorio.findById(id)
                        .orElseThrow(() -> new RuntimeException("Usuário com ID: " + id + " Não encontrado"));

        // Atualiza nome se for diferente
        Optional.ofNullable(dados.nome())
                .filter(novoNome -> !novoNome.isBlank() && !novoNome.equals(editarUsuario.getNome()))
                .ifPresent(editarUsuario::setNome);

        // Atualiza email se for diferente
        Optional.ofNullable(dados.email())
                .filter(novoEmail -> !novoEmail.isBlank() && !novoEmail.equals(editarUsuario.getEmail()))
                .ifPresent(editarUsuario::setEmail);

        Optional.ofNullable(dados.senha())
                .filter(novaSenha -> !novaSenha.isBlank() && !new BCryptPasswordEncoder().matches(novaSenha, editarUsuario.getSenha()))
                .ifPresent(novaSenha -> {
                    String senhaCriptografada = new BCryptPasswordEncoder().encode(novaSenha);
                    editarUsuario.setSenha(senhaCriptografada);
                });

        Optional.ofNullable(dados.avatar_id()).ifPresent(avatarId -> {
            try {
                // Busca uma REFERÊNCIA para o avatar. É mais eficiente que findById().
                EntidadeAvatar avatar = avatarRepositorio.getReferenceById(avatarId);
                // Associa a referência da entidade encontrada ao usuário.
                editarUsuario.setAvatar(avatar);
            } catch (EntityNotFoundException e) {
                // Se o avatar_id fornecido não existe no banco, a requisição é inválida.
                throw new RuntimeException("Avatar com ID " + avatarId + " não encontrado.");
            }
        });

        EntidadeUsuario usuarioSalvo = usuarioRepositorio.save(editarUsuario); // salva alterações
        return new UsuarioDTO(usuarioSalvo);
    }







}



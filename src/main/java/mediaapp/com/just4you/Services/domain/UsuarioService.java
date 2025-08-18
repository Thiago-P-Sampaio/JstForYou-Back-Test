package mediaapp.com.just4you.Services.domain;

import mediaapp.com.just4you.DTOs.Response.UsuarioDTO;
import mediaapp.com.just4you.Entities.EntidadeUsuario;
import mediaapp.com.just4you.Repositories.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepositorio usuarioRepositorio;

 // GET
    @Transactional(readOnly = true)
    public UsuarioDTO buscarUsuarioPorId(Long id){
        EntidadeUsuario usuario = usuarioRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));


        return new UsuarioDTO(usuario);
    }

    public boolean deletarUsuario(Long id){
        Optional<EntidadeUsuario> usuario = usuarioRepositorio.findById(id);
        if(usuario.isPresent()){
            usuarioRepositorio.deleteById(id);
            return true;
        }
        return false;
    }







}



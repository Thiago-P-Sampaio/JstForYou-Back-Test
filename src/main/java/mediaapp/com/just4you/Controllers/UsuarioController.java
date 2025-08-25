package mediaapp.com.just4you.Controllers;

import mediaapp.com.just4you.DTOs.Response.UsuarioDTO;
import mediaapp.com.just4you.Entities.EntidadeUsuario;
import mediaapp.com.just4you.Services.domain.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("api/jfy/user")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

// Buscar usuário por ID
@GetMapping("/{id}")
public ResponseEntity<UsuarioDTO> buscarUsuarioPorId(@PathVariable Long id) {
    UsuarioDTO usuario = usuarioService.buscarUsuarioPorId(id);
    return ResponseEntity.ok(usuario);
}

    // Deletar usuário por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        boolean deletado = usuarioService.deletarUsuario(id);
        if (deletado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}

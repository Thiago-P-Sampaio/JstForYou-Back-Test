package mediaapp.com.just4you.Controllers;

import jakarta.validation.Valid;
import mediaapp.com.just4you.DTOs.Create.EditarUsuario;
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
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorId(id));
    }

    // Deletar usuário por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        usuarioService.deletarUsuario(id);
        return ResponseEntity.noContent().build();

    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<UsuarioDTO> alterarDadosUsuario(
            @PathVariable Long id,
            @RequestBody @Valid EditarUsuario dados) {

        return ResponseEntity.ok().body(usuarioService.alterarDadosUsuario(dados, id));
    }


}

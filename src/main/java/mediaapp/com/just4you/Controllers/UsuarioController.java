package mediaapp.com.just4you.Controllers;

import jakarta.validation.Valid;
import mediaapp.com.just4you.DTOs.Put.AlterarSenha;
import mediaapp.com.just4you.DTOs.Put.EditarUsuario;
import mediaapp.com.just4you.DTOs.Response.UsuarioDTO;
import mediaapp.com.just4you.Services.domain.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<Page<UsuarioDTO>> buscarTodosPorPagina(Pageable pagina){
        Page<UsuarioDTO> paginaDeUsuarios = usuarioService.buscarTodosPorPagina(pagina);
        return ResponseEntity.ok().body(paginaDeUsuarios);
    }

    // Deletar usuário por ID
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        usuarioService.deletarUsuario(id);
        return ResponseEntity.noContent().build();

    }

//    @PreAuthorize("#id == authentication.principal.id")
    @PutMapping("/edit/{id}")
    public ResponseEntity<UsuarioDTO> alterarDadosUsuario(
            @PathVariable Long id,
            @RequestBody @Valid EditarUsuario dados) {

        return ResponseEntity.ok().body(usuarioService.alterarDadosUsuario(dados, id));
    }

    @PutMapping("/change-password/{id}")
    public ResponseEntity<?> alterarSenhaUsuario( @RequestBody @Valid AlterarSenha dados, @PathVariable Long id) {
        usuarioService.alterarSenha(dados, id);
        return ResponseEntity.noContent().build();
    }


}

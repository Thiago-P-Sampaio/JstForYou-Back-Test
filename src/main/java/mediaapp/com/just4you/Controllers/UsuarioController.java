package mediaapp.com.just4you.Controllers;

import jakarta.validation.Valid;
import mediaapp.com.just4you.Entities.EntidadeUsuario;
import mediaapp.com.just4you.Repositories.UsuarioRepositorio;
import mediaapp.com.just4you.Services.UsuarioService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping (value = "/user")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;


    @GetMapping("/buscar")
    public ResponseEntity<List<EntidadeUsuario>> buscar(){
        return ResponseEntity.ok().body(usuarioService.buscarUsuarios());
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity buscarUsuario(@PathVariable Long id){
        EntidadeUsuario usuario = usuarioService.buscarUsuario(id);
        if(usuario != null)return ResponseEntity.ok(usuario);
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
    }


}

package mediaapp.com.just4you.Controllers;

import mediaapp.com.just4you.DTOs.Create.AdicionarAvatarDTO;
import mediaapp.com.just4you.DTOs.Response.AvatarDTO;
import mediaapp.com.just4you.Entities.EntidadeAvatar;
import mediaapp.com.just4you.Services.domain.AvatarService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/jfy/avatar")
public class AvatarController {

    @Autowired
    AvatarService avatarService;


    @PostMapping("/add")
    public ResponseEntity<AvatarDTO> adicionarAvatar(@RequestBody AdicionarAvatarDTO dto) {
        return ResponseEntity.ok().body(avatarService.novoAvatar(dto));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<AvatarDTO> editarAvatar(@PathVariable Long id, @RequestBody AdicionarAvatarDTO dto) {
        return ResponseEntity.ok().body(avatarService.atualizarAvatar(dto, id));
    }

    @DeleteMapping("/dell/{id}")
    public ResponseEntity<?> deletarAvatar(@PathVariable Long id) {
        avatarService.deletarAvatar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<AvatarDTO> buscarAvatar(@PathVariable Long id){
        return ResponseEntity.ok().body(avatarService.buscarAvatarPorId(id));
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<AvatarDTO>> buscarAvatares(){
        return ResponseEntity.ok().body(avatarService.listarAvatars());
    }
}

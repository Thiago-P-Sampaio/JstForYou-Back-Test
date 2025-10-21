package mediaapp.com.just4you.Controllers;

import jakarta.validation.Valid;
import mediaapp.com.just4you.DTOs.Create.AdicionarAvatarDTO;
import mediaapp.com.just4you.DTOs.Put.EditarAvatar;
import mediaapp.com.just4you.DTOs.Response.AvatarDTO;
import mediaapp.com.just4you.Entities.EntidadeAvatar;
import mediaapp.com.just4you.Services.domain.AvatarService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/jfy/avatar")
public class AvatarController {

    @Autowired
    AvatarService avatarService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<AvatarDTO> adicionarAvatar(@RequestBody @Valid  AdicionarAvatarDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(avatarService.novoAvatar(dto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/edit/{id}")
    public ResponseEntity<AvatarDTO> editarAvatar(@PathVariable Long id, @RequestBody @Valid EditarAvatar dto) {
        return ResponseEntity.ok().body(avatarService.atualizarAvatar(dto, id));
    }

    @PreAuthorize("hasRole('ADMIN')")
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

    @GetMapping("/get")
    public ResponseEntity<Page<AvatarDTO>> listarAvataresPaginacao(Pageable paginacao){
        Page<AvatarDTO> avataresPagina = avatarService.listarAvataresPaginacao(paginacao);
        return ResponseEntity.ok().body(avataresPagina);
    }
}

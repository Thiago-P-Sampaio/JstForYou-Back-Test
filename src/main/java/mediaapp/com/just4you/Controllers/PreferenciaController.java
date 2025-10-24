package mediaapp.com.just4you.Controllers;


import jakarta.validation.Valid;
import mediaapp.com.just4you.DTOs.Create.CriarPreferenciaDTO;
import mediaapp.com.just4you.DTOs.Put.EditarPreferencia;
import mediaapp.com.just4you.DTOs.Response.PreferenciaDTO;
import mediaapp.com.just4you.Entities.EntidadePreferencia;
import mediaapp.com.just4you.Services.domain.PreferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/jfy/preferences")
public class PreferenciaController {

    @Autowired
    PreferenciaService preferenciaService;


    @PostMapping("/usuario")
    public ResponseEntity<?> criarPreferencia(
            @RequestBody @Valid CriarPreferenciaDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(preferenciaService.criarPreferencia(dto));
    }

    @DeleteMapping("/{preferenciaId}")
    public ResponseEntity<Void> deletarPreferencia(@PathVariable Long preferenciaId) {
        preferenciaService.deletarPreferencia(preferenciaId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<PreferenciaDTO>> buscarPreferenciasPorUsuario(@PathVariable Long usuarioId) {
        List<PreferenciaDTO> preferencias = preferenciaService.buscarPreferenciasPorUsuario(usuarioId);
        return ResponseEntity.ok(preferencias);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<PreferenciaDTO>> listarPreferencias(Pageable paginacao) {
        Page<PreferenciaDTO> preferencias = preferenciaService.listasPreferenciasPaginado(paginacao);
        return ResponseEntity.ok(preferencias);
    }

    @PutMapping("/edit/{id}/{usuarioId}")
    public ResponseEntity<PreferenciaDTO> atualizarPreferencia(
            @PathVariable Long id,
            @PathVariable Long usuarioId,
            @RequestBody @Valid  EditarPreferencia dto
    ){
        return ResponseEntity.ok(preferenciaService.editarPreferencia(dto, id, usuarioId));
    }



}

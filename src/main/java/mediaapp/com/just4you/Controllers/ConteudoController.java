package mediaapp.com.just4you.Controllers;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import mediaapp.com.just4you.DTOs.Create.CriarConteudoDTO;
import mediaapp.com.just4you.DTOs.Put.EditarConteudo;
import mediaapp.com.just4you.DTOs.Response.ConteudoDTO;
import mediaapp.com.just4you.Entities.EntidadeConteudos;
import mediaapp.com.just4you.Services.domain.ConteudoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/jfy/content")
public class ConteudoController {

    @Autowired
    ConteudoService conteudoService;


    // Criar conteúdo
    @PostMapping
    public ResponseEntity<ConteudoDTO> adicionarConteudo(@RequestBody @Valid CriarConteudoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(conteudoService.adicionarConteudo(dto));
    }

    // Deletar conteúdo
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/dell/{id}")
    public ResponseEntity<Void> deletarConteudo(@PathVariable Long id) {
        conteudoService.deletarConteudo(id);
        return ResponseEntity.noContent().build();
    }

    // Listar todos os conteúdos
    @GetMapping("/all")
    public ResponseEntity<Page<ConteudoDTO>> listarConteudos(Pageable paginacao) {
        Page<ConteudoDTO> conteudos = conteudoService.listarConteudos(paginacao);
        return ResponseEntity.ok().body(conteudos);
    }

    // Buscar conteúdo por ID interno
    @GetMapping("/get/{id}")
    public ResponseEntity<ConteudoDTO> buscarConteudoPorId(@PathVariable Long id) {
        ConteudoDTO conteudo = conteudoService.buscarConteudoPorId(id);
        return ResponseEntity.ok(conteudo);
    }

    // Buscar por mediaId + tipoMedia (do TMDB)
    // /conteudos/media?mediaId=872585&tipoMedia=MOVIE
    @GetMapping("/media")
    public ResponseEntity<ConteudoDTO> buscarPorMediaComId(
            @RequestParam Long mediaId,
            @RequestParam String tipoMedia) {
        ConteudoDTO conteudo = conteudoService.buscarPorMediaComId(mediaId, tipoMedia);
        return ResponseEntity.ok(conteudo);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/edit/{id}")
    public ResponseEntity<ConteudoDTO> editarConteudo(@PathVariable Long id, @RequestBody @Valid EditarConteudo dto) {
        return ResponseEntity.ok().body(conteudoService.editarConteudo(dto, id));
    }

    }


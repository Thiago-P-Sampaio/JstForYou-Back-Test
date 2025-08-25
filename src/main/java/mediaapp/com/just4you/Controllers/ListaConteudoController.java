package mediaapp.com.just4you.Controllers;


import jakarta.validation.Valid;
import mediaapp.com.just4you.DTOs.Create.ConteudoParaListaDTO;
import mediaapp.com.just4you.DTOs.Response.ListaUsuarioDTO;
import mediaapp.com.just4you.Services.domain.ListaConteudoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/jfy/listcontent")
public class ListaConteudoController {

    @Autowired
    ListaConteudoService listaConteudoService;

    // Adicionar conteúdo na lista do usuário
    @PostMapping("/usuario/{usuarioId}/conteudos")
    public ResponseEntity<Void> adicionarConteudoLista(
            @PathVariable Long usuarioId,
            @RequestBody @Valid ConteudoParaListaDTO dto) {
        listaConteudoService.adicionarConteudoLista(dto, usuarioId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // Remover conteúdo da lista do usuário
    @DeleteMapping("/usuario/{usuarioId}/conteudos")
    public ResponseEntity<Void> removerConteudoLista(
            @PathVariable Long usuarioId,
            @RequestBody @Valid ConteudoParaListaDTO dto) {
        listaConteudoService.removerConteudoLista(dto, usuarioId);
        return ResponseEntity.noContent().build();
    }

    // Buscar lista de um usuário
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<ListaUsuarioDTO> buscarListaPorUsuario(@PathVariable Long usuarioId) {
        ListaUsuarioDTO listaDto = listaConteudoService.buscarListaPorUsuario(usuarioId);
        return ResponseEntity.ok(listaDto);
    }
}

package mediaapp.com.just4you.Controllers;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import mediaapp.com.just4you.DTOs.Create.CriarConteudoDTO;
import mediaapp.com.just4you.Services.domain.ConteudoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/jfy/content")
public class ConteudoController {

    @Autowired
    ConteudoService conteudoService;

    @PostMapping("/add")
    public ResponseEntity<?> adicionarConteudo(CriarConteudoDTO dto){
        return ResponseEntity.ok(conteudoService.adicionarConteudo(dto));
    }

    @DeleteMapping("/dell/{id}")
    public void deletarConteudo(@PathVariable Long id){
        conteudoService.deletarConteudo(id);
    }

    @GetMapping("get/all")
        public ResponseEntity<List<?>> buscarTodosConteudos(){
            return ResponseEntity.ok(conteudoService.listarConteudos());
        }

        @GetMapping("get/type/{id}/with/{media}")
        public  ResponseEntity<?> buscarPorMediaComId(@PathVariable Long id, @PathVariable String media){
            return ResponseEntity.ok(conteudoService.buscarPorMediaComId(id, media));
        }

    }


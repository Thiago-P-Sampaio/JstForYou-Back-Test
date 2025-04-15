package mediaapp.com.just4you.Controllers;

import jakarta.validation.Valid;
import mediaapp.com.just4you.DTOs.AddPreferenciaDTO;
import mediaapp.com.just4you.DTOs.PreferenciaDTO;
import mediaapp.com.just4you.Services.PreferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/preferencia")
public class PreferenciaController {

    @Autowired
    PreferenciaService preferenciaService;


    @PostMapping("/add/{id}")
    public ResponseEntity addPreferencia(@PathVariable Long id, @RequestBody @Valid AddPreferenciaDTO dto){
        return ResponseEntity.ok(preferenciaService.addPreferencia(id, dto));

    }
}

package mediaapp.com.just4you.Controllers;

import mediaapp.com.just4you.Services.RelatorioService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/relatorios")
public class RelatorioController {


    @Autowired
    RelatorioService relatorioService;

    @GetMapping
    public ResponseEntity<String> gerarRelatorio(@RequestParam String destiny) throws JRException {
        relatorioService.gerarRelatorioPDF(destiny);
        return  ResponseEntity.ok("Relatório gerado com SUCESSO em: " + destiny );

    }
}

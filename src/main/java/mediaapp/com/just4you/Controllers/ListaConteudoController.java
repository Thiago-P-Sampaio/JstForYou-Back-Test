package mediaapp.com.just4you.Controllers;


import mediaapp.com.just4you.Services.domain.ListaConteudoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/jfy/listcontent")
public class ListaConteudoController {

    @Autowired
    ListaConteudoService listaConteudoService;
}

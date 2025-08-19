package mediaapp.com.just4you.Controllers.Access;


import mediaapp.com.just4you.Services.domain.PreferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/jfy/preferences")
public class PreferenciaController {

    @Autowired
    PreferenciaService preferenciaService;
}

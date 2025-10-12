package mediaapp.com.just4you.Configurations.Security;

import mediaapp.com.just4you.Entities.EntidadeListaUsuario;
import mediaapp.com.just4you.Entities.EntidadeUsuario;
import mediaapp.com.just4you.Repositories.ListaUsuarioRepositorio;
import mediaapp.com.just4you.Repositories.UsuarioRepositorio;
import mediaapp.com.just4you.Roles.PermissaoUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;

@Component
public class InicializadorAdmin implements CommandLineRunner {

    @Autowired
    private  UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ListaUsuarioRepositorio listaUsuarioRepositorio;

    @Value("${api.secret.admin.email}")
    private String adminEmail;

    @Value("${api.secret.admin.password}")
    private String adminPassword;




    @Override
    public void run(String... args) throws Exception {
        if(usuarioRepositorio.existsByRole(PermissaoUsuario.ADMIN)){
            return;
        }

        EntidadeUsuario admin = new EntidadeUsuario();
        admin.setEmail(adminEmail);
        admin.setSenha(passwordEncoder.encode(adminPassword));
        admin.setNome("Admin");
        admin.setDataNascimento(LocalDate.now());
        admin.setRole(PermissaoUsuario.ADMIN);
        admin.setDataCadastro(Instant.now());

        usuarioRepositorio.save(admin);

        EntidadeListaUsuario entidadeListaUsuario = new EntidadeListaUsuario();
        entidadeListaUsuario.setUsuario(admin);

        listaUsuarioRepositorio.save(entidadeListaUsuario);

    }
}

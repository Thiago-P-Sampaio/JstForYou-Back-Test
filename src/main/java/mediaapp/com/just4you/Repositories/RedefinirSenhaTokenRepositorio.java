package mediaapp.com.just4you.Repositories;

import mediaapp.com.just4you.Entities.EntidadeUsuario;
import mediaapp.com.just4you.Entities.RedefinirSenhaToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RedefinirSenhaTokenRepositorio extends JpaRepository<RedefinirSenhaToken, Long> {

    RedefinirSenhaToken findByToken(String token);
    Optional<RedefinirSenhaToken> findByUsuario(EntidadeUsuario usuario);
}

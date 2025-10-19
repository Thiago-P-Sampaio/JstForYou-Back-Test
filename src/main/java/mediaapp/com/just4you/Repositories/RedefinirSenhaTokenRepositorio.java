package mediaapp.com.just4you.Repositories;

import mediaapp.com.just4you.Entities.EntidadeUsuario;
import mediaapp.com.just4you.Entities.RedefinirSenhaToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RedefinirSenhaTokenRepositorio extends JpaRepository<RedefinirSenhaToken, Long> {

    RedefinirSenhaToken findByToken(String token);
    void deleteByUsuarioId(Long usuario_id);
}

package mediaapp.com.just4you.Repositories;

import mediaapp.com.just4you.Entities.EntidadeUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<EntidadeUsuario, Long> {
}

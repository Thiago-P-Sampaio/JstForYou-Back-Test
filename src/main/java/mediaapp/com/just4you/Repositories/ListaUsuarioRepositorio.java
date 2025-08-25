package mediaapp.com.just4you.Repositories;

import mediaapp.com.just4you.Entities.EntidadeListaUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ListaUsuarioRepositorio extends JpaRepository<EntidadeListaUsuario, Long> {

   Optional<EntidadeListaUsuario> findByUsuario_UsuarioId(Long usuarioId);
}

package mediaapp.com.just4you.Repositories;

import mediaapp.com.just4you.Entities.EntidadePreferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PreferenciaRepositorio  extends JpaRepository<EntidadePreferencia, Long> {


    List<EntidadePreferencia> findByUsuario_UsuarioId(Long idUsuario);
}

package mediaapp.com.just4you.Repositories;

import mediaapp.com.just4you.Entities.EntidadePreferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreferenciaRepositorio  extends JpaRepository<EntidadePreferencia, Long> {
}

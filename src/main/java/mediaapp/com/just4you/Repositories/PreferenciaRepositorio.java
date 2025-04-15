package mediaapp.com.just4you.Repositories;

import mediaapp.com.just4you.Entities.EntidadePreferencias;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreferenciaRepositorio extends JpaRepository
        <EntidadePreferencias, Long> {
}

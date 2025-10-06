package mediaapp.com.just4you.Repositories;

import mediaapp.com.just4you.Entities.EntidadeAvatar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AvatarRepositorio extends JpaRepository<EntidadeAvatar, Long > {

    Optional<EntidadeAvatar> existsByUrl(String url);
}

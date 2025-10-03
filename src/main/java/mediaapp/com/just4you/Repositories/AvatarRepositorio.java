package mediaapp.com.just4you.Repositories;

import mediaapp.com.just4you.Entities.EntidadeAvatar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvatarRepositorio extends JpaRepository<EntidadeAvatar, Long > {

    boolean existsByUrl(String url);
}

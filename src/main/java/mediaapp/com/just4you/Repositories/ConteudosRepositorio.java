package mediaapp.com.just4you.Repositories;

import mediaapp.com.just4you.Entities.EntidadeConteudos;
import mediaapp.com.just4you.Entities.TipoMedia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConteudosRepositorio extends JpaRepository<EntidadeConteudos, Long> {

//    boolean findByMediaAndMediaId(TipoMedia media, Long mediaId);

   Optional<EntidadeConteudos> findByMediaIdAndMedia(Long mediaId, TipoMedia media);
}

package mediaapp.com.just4you.Repositories;

import mediaapp.com.just4you.Entities.EntidadeConteudos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConteudosRepositorio extends JpaRepository<EntidadeConteudos, Long> {

    String findByTitulo(String titulo);
}

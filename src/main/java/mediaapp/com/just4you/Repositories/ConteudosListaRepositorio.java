package mediaapp.com.just4you.Repositories;

import mediaapp.com.just4you.Entities.EntidadeListaConteudo;
import mediaapp.com.just4you.Entities.EntidadeListaConteudoPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConteudosListaRepositorio extends JpaRepository<EntidadeListaConteudo, EntidadeListaConteudoPK> {
}

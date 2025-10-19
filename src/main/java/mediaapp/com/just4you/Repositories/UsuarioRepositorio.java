package mediaapp.com.just4you.Repositories;

import mediaapp.com.just4you.Entities.EntidadeAvatar;
import mediaapp.com.just4you.Entities.EntidadeUsuario;
import mediaapp.com.just4you.Roles.PermissaoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepositorio extends JpaRepository<EntidadeUsuario, Long> {

    UserDetails findByEmail(String email);

    Optional<EntidadeUsuario> findEntidadeUsuarioByEmail(String email);


    @Modifying
    @Query("UPDATE EntidadeUsuario u SET u.avatar = NULL WHERE u.avatar = :avatar")
    void desassociarAvatar(@Param("avatar")EntidadeAvatar avatar);


    boolean existsByRole(PermissaoUsuario role);
}

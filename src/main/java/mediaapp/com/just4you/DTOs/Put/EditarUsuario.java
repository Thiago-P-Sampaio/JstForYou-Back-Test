package mediaapp.com.just4you.DTOs.Put;

public record EditarUsuario(

        String nome,
        String email,
        String senha,
        Long avatar_id
) {

}

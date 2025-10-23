package mediaapp.com.just4you.DTOs.Put;


import mediaapp.com.just4you.Validators.annotations.Texto;
import org.hibernate.validator.constraints.URL;

public record EditarAvatar(

        @URL
        String url,
        @Texto
        String descricao
) {
}

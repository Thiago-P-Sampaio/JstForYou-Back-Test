package mediaapp.com.just4you.DTOs.Put;


import mediaapp.com.just4you.Validators.annotations.DominioValido;
import mediaapp.com.just4you.Validators.annotations.Texto;
import org.hibernate.validator.constraints.URL;

public record EditarAvatar(

        @URL
        @DominioValido( dominiosPermitidos = "res.cloudinary.com",
                protocolosPermitidos = "https",
                message = "A URL informada não pertence " +
                        "ao domínio aceito ou não está situada no protocolo HTTPS"
        )
        String url,
        @Texto
        String descricao
) {
}

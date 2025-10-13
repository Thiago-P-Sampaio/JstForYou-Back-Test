package mediaapp.com.just4you.DTOs.Exception;

import java.time.Instant;

public record ErroResposta(

        Instant timestamp,
        Integer status,
        String error,
        String message,
        String path
) {
}

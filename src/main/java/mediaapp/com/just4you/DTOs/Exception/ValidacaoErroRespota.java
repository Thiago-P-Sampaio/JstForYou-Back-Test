package mediaapp.com.just4you.DTOs.Exception;

import java.time.Instant;
import java.util.Map;

public record ValidacaoErroRespota(

        Instant timestamp,
        Integer status,
        String error,
        Map<String, String> message,
        String path
) {
}

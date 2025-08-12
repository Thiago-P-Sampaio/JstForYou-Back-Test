package mediaapp.com.just4you.Services.SecurityServices;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import mediaapp.com.just4you.Entities.EntidadeUsuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.secret.token.secret}")
    private String secretKey;

    public  String gerarToken(EntidadeUsuario usuario){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            String token = JWT.create()
                    .withIssuer("jst4you")
                    .withSubject(usuario.getEmail())
                    .withExpiresAt(tempoExpiracao())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception){
            throw new RuntimeException("Error ao gerar o token", exception);
        }
    }


    public String validarToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            return  JWT.require(algorithm)
                    .withIssuer("jst4you")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception){
            return "";

        }

    }

    private Instant tempoExpiracao(){
        return LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-03:00"));
    }

}

package mediaapp.com.just4you.Configurations.Security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mediaapp.com.just4you.Repositories.UsuarioRepositorio;
import mediaapp.com.just4you.Services.SecurityServices.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class FiltroSeguranca extends OncePerRequestFilter {

    @Autowired
    TokenService tokenService;

    @Autowired
    UsuarioRepositorio usuarioRepositorio;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        var token = recoverToken(request);

        try {
            if (token != null) {
                var email = tokenService.validarToken(token);

                UserDetails usuario = usuarioRepositorio.findByEmail(email);

                var autenticacao = new UsernamePasswordAuthenticationToken(
                        usuario, null, usuario.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(autenticacao);
            }
        } catch (RuntimeException e) {

            SecurityContextHolder.clearContext();


            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        var headerAuth = request.getHeader("Authorization");
        if (headerAuth == null || !headerAuth.startsWith("Bearer ")) return null;
        return headerAuth.replace("Bearer ", "");
    }

}

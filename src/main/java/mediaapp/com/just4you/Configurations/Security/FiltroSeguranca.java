package mediaapp.com.just4you.Configurations.Security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mediaapp.com.just4you.Repositories.UsuarioRepositorio;
import mediaapp.com.just4you.Services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.Security;

@Component
public class FiltroSeguranca extends OncePerRequestFilter {

    @Autowired
    TokenService tokenService;

    @Autowired
    UsuarioRepositorio usuarioRepositorio;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);
        if(token != null){
            var returnEmail = tokenService.validarToken(token);
            UserDetails usuario = usuarioRepositorio.findByEmail(returnEmail);

            var autenticacao = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(autenticacao);
        }
        filterChain.doFilter(request, response);
    }

    private  String recoverToken(HttpServletRequest request){
        var HeaderAuth = request.getHeader("Authorization");
        if(HeaderAuth == null) return null;
        return HeaderAuth.replace("Bearer ", "");
    }
}

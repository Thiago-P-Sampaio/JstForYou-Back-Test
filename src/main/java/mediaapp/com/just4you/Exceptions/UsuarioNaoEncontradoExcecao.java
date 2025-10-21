package mediaapp.com.just4you.Exceptions;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UsuarioNaoEncontradoExcecao extends UsernameNotFoundException {
    public UsuarioNaoEncontradoExcecao(String message) {
        super(message);
    }
}

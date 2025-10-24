package mediaapp.com.just4you.Services.SecurityServices;

import mediaapp.com.just4you.Entities.EntidadeUsuario;
import mediaapp.com.just4you.Exceptions.AcessoNegadoExcecao;
import mediaapp.com.just4you.Exceptions.RecursoNaoEncontradoExcecao;
import mediaapp.com.just4you.Repositories.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioAutenticadoService {

    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    public EntidadeUsuario checarPermissaoEObterUsuario(Long usuarioId) {

        Authentication autenticacao = SecurityContextHolder.getContext().getAuthentication();
        String emailAutenticado = autenticacao.getName();

        EntidadeUsuario usuarioAutenticado = usuarioRepositorio.findEntidadeUsuarioByEmail(emailAutenticado)
                .orElseThrow(() -> new RecursoNaoEncontradoExcecao("Usuário autenticado não encontrado"));

        boolean usuarioProprio =  usuarioAutenticado.getUsuarioId().equals(usuarioId);
        boolean usuarioAdmin = autenticacao.getAuthorities().stream()
                .anyMatch(autoridade -> autoridade.getAuthority().equals("ROLE_ADMIN"));

        if (!usuarioProprio && !usuarioAdmin) {
            throw  new AcessoNegadoExcecao("Acesso negado! Você não tem permissão para realizar essa operação");
        }

        if (usuarioAdmin && !usuarioProprio) {
            return usuarioRepositorio.findById(usuarioId)
                    .orElseThrow(() -> new RecursoNaoEncontradoExcecao(("Usuário com ID: " + usuarioId + " não encontrado!")));
        } else {
            return usuarioAutenticado;
        }
    }

    public void checarPermissao(Long usuarioId) {
        Authentication autenticacao = SecurityContextHolder.getContext().getAuthentication();
        String emailAutenticado = autenticacao.getName();

        EntidadeUsuario usuarioAutenticado = usuarioRepositorio.findEntidadeUsuarioByEmail(emailAutenticado)
                .orElseThrow(() -> new RecursoNaoEncontradoExcecao("Usuário autenticado não encontrado"));

        boolean preferenciaUsuario = usuarioAutenticado.getUsuarioId().equals(usuarioId);
        boolean usuarioAdmin = autenticacao.getAuthorities().stream()
                .anyMatch(autoridade -> autoridade.getAuthority().equals("ROLE_ADMIN"));
        if(!usuarioAdmin && !preferenciaUsuario) {
            throw new AcessoNegadoExcecao("Acesso negado! Você não tem permissão para realizar essa operação");
        }
    }
}

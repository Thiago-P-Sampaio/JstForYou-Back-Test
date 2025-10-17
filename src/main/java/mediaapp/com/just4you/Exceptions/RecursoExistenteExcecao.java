package mediaapp.com.just4you.Exceptions;

public class RecursoExistenteExcecao extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public RecursoExistenteExcecao(String mensagem) {
        super(mensagem);
    }
}

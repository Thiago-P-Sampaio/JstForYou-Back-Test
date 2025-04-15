package mediaapp.com.just4you.DTOs;

public class AddPreferenciaDTO {

    private String descricao;

    public AddPreferenciaDTO(String descricao) {
        this.descricao = descricao;
    }

    public AddPreferenciaDTO() {
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}

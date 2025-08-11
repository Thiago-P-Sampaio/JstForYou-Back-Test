package mediaapp.com.just4you.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table (name = "tb_conteudos")
public class EntidadeConteudos {

    private Long conteudoId;
    private String titulo;

}

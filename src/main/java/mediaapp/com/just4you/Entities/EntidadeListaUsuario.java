package mediaapp.com.just4you.Entities;


import jakarta.persistence.*;

@Entity
@Table (name = "tb_lista_usuario")
public class EntidadeListaUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long listaId;

}

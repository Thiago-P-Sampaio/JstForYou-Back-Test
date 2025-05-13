package mediaapp.com.just4you.DTOs;

import mediaapp.com.just4you.Entities.EntidadeUsuario;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

public class RelatorioUserDTO {

    private Long usuarioId;
    private  String preferencia;
    private Long  faixaEtaria;


   public RelatorioUserDTO(EntidadeUsuario entity){
        this.usuarioId = entity.getUsuarioId();
        this.preferencia = entity.getPreferencias().stream()
                .map(pref -> pref.getDescricao())  // Ajuste conforme o campo desejado
                .collect(Collectors.joining(", "));;

       LocalDate dataNascimento = entity.getDataNascimento();
       LocalDate dataAtual = LocalDate.now();
       this.faixaEtaria = Long.valueOf(ChronoUnit.YEARS.between(dataNascimento, dataAtual));
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public String getPreferencia() {
        return preferencia;
    }

    public Long getFaixaEtaria() {
        return faixaEtaria;
    }


}

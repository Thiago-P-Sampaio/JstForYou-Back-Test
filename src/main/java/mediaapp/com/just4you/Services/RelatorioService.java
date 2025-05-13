package mediaapp.com.just4you.Services;

import jakarta.transaction.Transactional;
import mediaapp.com.just4you.DTOs.RelatorioUserDTO;
import mediaapp.com.just4you.Entities.EntidadePreferencias;
import mediaapp.com.just4you.Entities.EntidadeUsuario;
import mediaapp.com.just4you.Repositories.PreferenciaRepositorio;
import mediaapp.com.just4you.Repositories.UsuarioRepositorio;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class RelatorioService {

    @Autowired
    UsuarioRepositorio usuarioRepositorio;



    @Transactional
    public void gerarRelatorioPDF(String destiny) throws JRException {

        List<EntidadeUsuario> preferenciaUsuarios = usuarioRepositorio.findAll();

        List<RelatorioUserDTO> dados = preferenciaUsuarios.stream()
                .map( RelatorioUserDTO :: new)
                .collect(Collectors.toList());

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dados);
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("titulo", " Relatório de Preferências ");

        JasperReport jasperReport = JasperCompileManager.compileReport(
                getClass().getResourceAsStream("/relatorios/relatorio_preferencia.jrxml"));

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, dataSource);

        JasperExportManager.exportReportToPdfFile(jasperPrint, destiny);
    }
    }

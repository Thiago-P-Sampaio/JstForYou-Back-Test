package mediaapp.com.just4you.Services.domain;


import jakarta.mail.internet.MimeMessage;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
public class EmailService {

    private final JavaMailSender mailSender;


    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void enviarEmail(String email, String token, String nome) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
                ///  PASSAR O EMAIL
            helper.setFrom("justforyou.tcc.senai@gmail.com");
            helper.setSubject("Alteração de Senha");
            helper.setTo(email);
                    ///  PASSAR A URL DO FRONT COM PARâMETRO  ?token=
            String resetUrl = "http://localhost:5173/redefinir-senha/" + token;
            String template  = carregarTemplateEmail();

            template = template.replace("#{nome}", nome);
            template = template.replace("#{link}", resetUrl);
            helper.setText(template, true);
            mailSender.send(message);
        } catch (Exception exception) {
            System.out.println("Falha ao enviar o email");
        }
    }


    private String carregarTemplateEmail() throws IOException {
        ClassPathResource recurso = new ClassPathResource("templates/index.html");
        return new String(recurso.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
    }


}

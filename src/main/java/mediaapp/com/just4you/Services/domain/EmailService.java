package mediaapp.com.just4you.Services.domain;


import mediaapp.com.just4you.DTOs.UserAcess.EnviarEmailRedefinirSenha;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void enviarEmail(String email, String token) {
        SimpleMailMessage mensagem = new SimpleMailMessage();
        mensagem.setFrom("noreply@email.com");
        mensagem.setTo(email);
        mensagem.setSubject("Teste de email");
        mensagem.setText("Esse email foi enviado com sucesso! \n utilize o Token: " + token);

        mailSender.send(mensagem);
    }


}

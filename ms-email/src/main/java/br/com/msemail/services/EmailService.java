package br.com.msemail.services;

import br.com.msemail.enums.StatusEmail;
import br.com.msemail.models.Email;
import br.com.msemail.repositories.EmailRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmailService {
    @Autowired
    private EmailRepository repository;

    @Autowired
    private JavaMailSender sender;


    @Value(value = "${spring.mail.username}")
    private String emailFrom;
    @Transactional
    public Email sendEmail(Email email){
        try{
            // Adicione logs para depuração
            System.out.println("Attempting to send email: " + email);

            email.setData(LocalDateTime.now());
            email.setEmailFrom(emailFrom);

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email.getEmailTo());
            message.setSubject(email.getSubject());
            message.setText(email.getText());
            sender.send(message);

            email.setStatusEmail(StatusEmail.ENVIADO);

            System.out.println("Email sent successfully: " + email);
        } catch (MailException e){
            System.out.println("Failed to send email. Exception: " + e.getMessage());
            e.printStackTrace(); // Adicione isso para imprimir a pilha de exceção no console
            email.setStatusEmail(StatusEmail.ERRO);
        } finally {
            // Adicione logs e retorne o e-mail salvo
            System.out.println("Saving email status to the database: " + email.getStatusEmail());
            return repository.save(email);
        }
    }

}

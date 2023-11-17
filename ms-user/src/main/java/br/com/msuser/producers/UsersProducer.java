package br.com.msuser.producers;

import br.com.msuser.dtos.EmailDto;
import br.com.msuser.models.Users;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UsersProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value(value = "${broker.queue.email.name}")
    private String routingKey;

    public void sendEmailMessage(Users users){
        var email = new EmailDto();
        email.setUserId(users.getId());
        email.setEmailTo(users.getEmail());
        email.setSubject("Cadastro realizado.");
        email.setText(users.getName() + ", seja bem vindo(a)! \n Agora que já está cadastrado em nossa plataforma," +
                                        " aproveite o máximo do nosso curso de " + users.getCurso());

        rabbitTemplate.convertAndSend("", routingKey, email);
    }

}

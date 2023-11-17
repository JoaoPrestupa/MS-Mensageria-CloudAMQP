package br.com.msemail.consumers;

import br.com.msemail.dtos.EmailRecordDto;
import br.com.msemail.models.Email;
import br.com.msemail.services.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {

    @Autowired
    private EmailService service;

    @RabbitListener(queues = "${broker.queue.email.name}" )
    public void litenEmailQueue(@Payload EmailRecordDto emailRecordDto){
        try {
            System.out.println("Received message from RabbitMQ: " + emailRecordDto);
            var email = new Email();
            BeanUtils.copyProperties(emailRecordDto, email);
            service.sendEmail(email);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}

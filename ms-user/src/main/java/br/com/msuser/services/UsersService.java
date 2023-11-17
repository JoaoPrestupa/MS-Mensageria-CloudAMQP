package br.com.msuser.services;

import br.com.msuser.models.Users;
import br.com.msuser.producers.UsersProducer;
import br.com.msuser.repositories.UsersRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    @Autowired
    private UsersRepository repository;
    @Autowired
    private UsersProducer producer;


    @Transactional
    public Users create(Users users){
        users = repository.save(users);
        producer.sendEmailMessage(users);

        return users;
    }

}

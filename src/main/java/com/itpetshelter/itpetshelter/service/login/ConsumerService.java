package com.itpetshelter.itpetshelter.service.login;

import com.itpetshelter.itpetshelter.domain.Consumer;
import com.itpetshelter.itpetshelter.repository.login.ConsumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    @Autowired
    private ConsumerRepository consumerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Consumer saveConsumer(Consumer consumer) {
        consumer.setCpw(passwordEncoder.encode(consumer.getCpw()));
        return consumerRepository.save(consumer);
    }

    public Consumer findByCid(String Cid) {
        return consumerRepository.findByCid(Cid);
    }
}
package com.itpetshelter.itpetshelter.service.login;

import com.itpetshelter.itpetshelter.domain.Consumer;
import com.itpetshelter.itpetshelter.dto.login.ConsumerDTO;
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

    public Consumer saveConsumer(ConsumerDTO consumerDTO) {
        Consumer consumer = Consumer.builder()
                .Cid(consumerDTO.getCid())
                .Cpw(passwordEncoder.encode(consumerDTO.getCpw()))  // 비밀번호 암호화
                .Cname(consumerDTO.getCname())
                .Cphone(consumerDTO.getCphone())
                .build();
        return consumerRepository.save(consumer);  // Consumer 저장
    }

    public ConsumerDTO findByCid(String Cid) {
        Consumer consumer = consumerRepository.findByCid(Cid);  // Cid로 Consumer 찾기
        if (consumer == null) {
            return null;
        }
        return ConsumerDTO.builder()
                .Cid(consumer.getCid())
                .Cpw(consumer.getCpw())
                .Cname(consumer.getCname())
                .Cphone(consumer.getCphone())
                .build();
    }
}

package com.itpetshelter.itpetshelter.service;

import com.itpetshelter.itpetshelter.domain.Consumer;
import com.itpetshelter.itpetshelter.dto.login.ConsumerDTO;
import com.itpetshelter.itpetshelter.repository.login.ConsumerRepository;
import com.itpetshelter.itpetshelter.service.login.ConsumerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class ConsumerServiceTest {

    @Autowired
    private ConsumerService consumerService;

    @MockBean
    private ConsumerRepository consumerRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Test
    public void testSaveConsumer() {
        ConsumerDTO consumerDTO = new ConsumerDTO("testCid", "testCpw", "testCname", "testCphone");
        Consumer consumer = Consumer.builder()
                .Cid(consumerDTO.getCid())
                .Cpw("encodedPassword")
                .Cname(consumerDTO.getCname())
                .Cphone(consumerDTO.getCphone())
                .build();

        Mockito.when(passwordEncoder.encode(consumerDTO.getCpw())).thenReturn("encodedPassword");
        Mockito.when(consumerRepository.save(any(Consumer.class))).thenReturn(consumer);

        Consumer savedConsumer = consumerService.saveConsumer(consumerDTO);

        assertNotNull(savedConsumer);
        assertEquals("testCid", savedConsumer.getCid());
        assertEquals("encodedPassword", savedConsumer.getCpw());
        assertEquals("testCname", savedConsumer.getCname());
        assertEquals("testCphone", savedConsumer.getCphone());
    }

    @Test
    public void testFindByCid() {

        String testCid = "testCid";
        Consumer consumer = Consumer.builder()
                .Cid(testCid)
                .Cpw(passwordEncoder.encode("testCpw"))
                .Cname("testCname")
                .Cphone("testCphone")
                .build();


        Mockito.when(consumerRepository.findByCid(testCid)).thenReturn(consumer);


        ConsumerDTO consumerDTO = consumerService.findByCid(testCid);


        assertNotNull(consumerDTO);
        assertEquals(testCid, consumerDTO.getCid());
        assertTrue(passwordEncoder.matches("testCpw", consumerDTO.getCpw()));
        assertEquals("testCname", consumerDTO.getCname());
        assertEquals("testCphone", consumerDTO.getCphone());
    }
}
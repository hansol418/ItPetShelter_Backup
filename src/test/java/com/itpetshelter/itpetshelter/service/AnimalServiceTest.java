package com.itpetshelter.itpetshelter.service;

import com.itpetshelter.itpetshelter.domain.Animal;
import com.itpetshelter.itpetshelter.dto.AnimalDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Log4j2
public class AnimalServiceTest {

    @Autowired
    AnimalService animalService;

    @Test
    public void test() {

//        AnimalDTO animalDTO = AnimalDTO.builder()
//                .Atype("s")
//                .Aage(1L)
//                .Adisease(true)
//                .Aneutered(true)
//                .Aname("뽀삐")
//                .Alocate("test")
//                .build();
//
//
//        animalService.entityToDTO()


    }
}


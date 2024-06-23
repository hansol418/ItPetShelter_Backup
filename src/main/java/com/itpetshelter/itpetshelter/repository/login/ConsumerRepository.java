package com.itpetshelter.itpetshelter.repository.login;

import com.itpetshelter.itpetshelter.domain.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsumerRepository extends JpaRepository<Consumer, String> {
    Consumer findByCid(String Cid);
}
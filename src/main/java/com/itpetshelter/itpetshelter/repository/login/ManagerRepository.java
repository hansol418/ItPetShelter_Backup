package com.itpetshelter.itpetshelter.repository.login;

import com.itpetshelter.itpetshelter.domain.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager, Long> {
    Manager findByMid(String Mid);
}
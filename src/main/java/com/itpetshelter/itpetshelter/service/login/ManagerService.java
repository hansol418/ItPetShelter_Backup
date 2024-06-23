package com.itpetshelter.itpetshelter.service.login;

import com.itpetshelter.itpetshelter.domain.Manager;
import com.itpetshelter.itpetshelter.repository.login.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerService {

    @Autowired
    private ManagerRepository managerRepository;

    public Manager findByMid(String Mid) {
        return managerRepository.findByMid(Mid);
    }
}

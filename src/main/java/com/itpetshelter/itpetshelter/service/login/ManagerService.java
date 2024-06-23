package com.itpetshelter.itpetshelter.service.login;

import com.itpetshelter.itpetshelter.domain.Manager;
import com.itpetshelter.itpetshelter.dto.login.ManagerDTO;
import com.itpetshelter.itpetshelter.repository.login.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerService {

    @Autowired
    private ManagerRepository managerRepository;

    public ManagerDTO findByMid(String Mid) {
        Manager manager = managerRepository.findByMid(Mid);  // Mid로 Manager 찾기
        if (manager == null) {
            return null;
        }
        return ManagerDTO.builder()
                .Mid(manager.getMid())
                .Mpw(manager.getMpw())
                .Mname(manager.getMname())
                .build();
    }
}

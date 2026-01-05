package com.example.study.service;

import com.example.study.dto.JoinDTO;
import com.example.study.entity.UserEntity;
import com.example.study.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void joinProcess(JoinDTO joinDTO) {

        String userName = joinDTO.getUserName();
        String password = joinDTO.getPassword();
        System.out.println("password = " + password);

        // null 체크 추가
        if (userName == null || password == null) {
            throw new IllegalArgumentException("사용자명과 비밀번호는 필수입니다.");
        }


        Boolean isExist = userRepository.existsByUserName(userName);

        if (isExist) {
            return;
        }

        UserEntity data = new UserEntity();
        data.setUserName(userName);
        data.setPassword(bCryptPasswordEncoder.encode(password));
        data.setRole("ROLE_ADMIN");

        userRepository.save(data);
    }

}

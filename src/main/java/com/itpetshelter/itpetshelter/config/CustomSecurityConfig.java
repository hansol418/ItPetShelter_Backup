package com.itpetshelter.itpetshelter.config;


import com.itpetshelter.itpetshelter.domain.Consumer;
import com.itpetshelter.itpetshelter.domain.Manager;
import com.itpetshelter.itpetshelter.dto.login.ConsumerDTO;
import com.itpetshelter.itpetshelter.dto.login.ManagerDTO;
import com.itpetshelter.itpetshelter.service.login.ConsumerService;
import com.itpetshelter.itpetshelter.service.login.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class CustomSecurityConfig {

    @Autowired
    private ConsumerService consumerService;

    @Autowired
    private ManagerService managerService;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            if ("admin".equals(username)) {
                ManagerDTO manager = managerService.findByMid(username);
                if (manager != null) {
                    return org.springframework.security.core.userdetails.User
                            .withUsername(manager.getMid())
                            .password(manager.getMpw())
                            .roles("ADMIN")
                            .build();
                }
            } else {
                ConsumerDTO consumer = consumerService.findByCid(username);
                if (consumer != null) {
                    return org.springframework.security.core.userdetails.User
                            .withUsername(consumer.getCid())
                            .password(consumer.getCpw())
                            .roles("USER")
                            .build();
                }
            }
            throw new UsernameNotFoundException("User not found");
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


        http.formLogin(
                formLogin -> formLogin.loginPage("/member/login").permitAll()
        );

        // 로그 아웃 설정.
        http.logout(
                logout -> logout.logoutUrl("/member/logout").logoutSuccessUrl("/member/login?logout")

        );

        //로그인 후, 성공시 리다이렉트 될 페이지 지정, 간단한 버전.
        http.formLogin(formLogin ->
                formLogin.defaultSuccessUrl("/board/list", true)
        );

        // 기본은 csrf 설정이 on, 작업시에는 끄고 작업하기.
        http.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable());

        // 특정 페이지에 접근 권한 설정.
        http.authorizeRequests()
                // 정적 자원 모두 허용.
                .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                // 리스트는 기본으로 다 들어갈수 있게., 모두 허용
                .requestMatchers("/", "/board/list", "/member/join", "/login", "/joinUser", "/joinForm", "/findAll", "/images/**").permitAll()
                // 로그인 후 확인 하기. 권한 예제) hasRole("USER"),hasRole("ADMIN")
                .requestMatchers("/board/register", "/board/read", "/board/update").authenticated()
                // 권한  관리자만, 예제로 , 수정폼은 권한이 관리자여야 함.
                .requestMatchers("/admin").hasRole("ADMIN")
                // 위의 접근 제어 목록 외의 , 다른 어떤 요청이라도 반드시 인증이 되어야 접근이 된다.
                .anyRequest().authenticated();
//                .anyRequest().permitAll();
        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }
}

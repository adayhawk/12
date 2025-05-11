package com.example.librarymanagementsystem.config;

import com.example.librarymanagementsystem.model.User;
import com.example.librarymanagementsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private UserRepository userRepository;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/login", "/register", "/css/**").permitAll()
                        .requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")  // 严格限制管理员
                        .requestMatchers("/user/**").hasAuthority("ROLE_USER")    // 严格限制用户
                        .anyRequest().denyAll()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .successHandler((request, response, authentication) -> {
                            // 根据角色动态跳转
                            if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
                                response.sendRedirect("/admin/books");
                            } else {
                                response.sendRedirect("/user/books");
                            }
                        })
                        .failureUrl("/login?error=true")
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout=true")
                )
                .csrf().disable();

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            User user = userRepository.findByUsername(username);
            if (user == null) throw new UsernameNotFoundException("用户不存在");

            // 添加 ROLE_ 前缀（必须与数据库中的角色字段匹配）
            String role = "ROLE_" + user.getRole().toUpperCase();
            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .authorities(role)
                    .build();
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
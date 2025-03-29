package com.algomau.bank.config;

import com.algomau.bank.domain.Address;
import com.algomau.bank.domain.Bank;
import com.algomau.bank.domain.User;
import com.algomau.bank.domain.UserAccount;
import com.algomau.bank.domain.repository.BankRepository;
import com.algomau.bank.domain.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.UUID;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class DataInitializer {

    private final UserAccountRepository userAccountRepository;
    private final BankRepository bankRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner seedEntities() {
        return args -> {
            UserAccount admin = UserAccount.builder()
                    .userName("admin")
                    .password(passwordEncoder.encode("admin"))
                    .user(User.builder()
                            .email("admin@algomau.com")
                            .type(User.Type.EMPLOYEE)
                            .sinNumber(UUID.randomUUID().toString())
                            .address(Address.builder()
                                    .city("admin")
                                    .country("CA")
                                    .phone("647-222-5555")
                                    .state("ON")
                                    .type(Address.Type.BUSINESS)
                                    .build())
                            .build())
                    .role(UserAccount.Role.ROLE_ADMIN)
                    .build();

            UserAccount user = UserAccount.builder()
                    .userName("user")
                    .password(passwordEncoder.encode("user"))
                    .user(User.builder()
                            .email("user@algomau.com")
                            .type(User.Type.CUSTOMER)
                            .sinNumber(UUID.randomUUID().toString())
                            .address(Address.builder()
                                    .city("user")
                                    .country("CA")
                                    .phone("647-222-5555")
                                    .state("ON")
                                    .type(Address.Type.BUSINESS)
                                    .build())
                            .build())
                    .role(UserAccount.Role.ROLE_USER)
                    .build();

            UserAccount teller = UserAccount.builder()
                    .userName("teller")
                    .password(passwordEncoder.encode("teller"))
                    .user(User.builder()
                            .email("teller@algomau.com")
                            .type(User.Type.EMPLOYEE)
                            .sinNumber(UUID.randomUUID().toString())
                            .address(Address.builder()
                                    .city("teller")
                                    .country("CA")
                                    .phone("647-222-5555")
                                    .state("ON")
                                    .type(Address.Type.PERSONAL)
                                    .build())
                            .build())
                    .role(UserAccount.Role.ROLE_TELLER)
                    .build();

            userAccountRepository.saveAll(List.of(admin, user, teller));
            log.info("✅ Admin,User, and teller users created.");

            Bank bank= Bank.builder()
                    .code("COSC")
                    .type(Bank.Type.COMMERICAL)
                    .address(Address.builder()
                            .city("Brampton")
                            .country("CA")
                            .phone("647-222-5555")
                            .state("ON")
                            .type(Address.Type.BUSINESS)
                            .build())
                    .name("Algoma Bank")
                    .branchNumber("3506")
                    .build();
            bankRepository.save(bank);
            log.info("✅ Bank created.");
        };
    }
}
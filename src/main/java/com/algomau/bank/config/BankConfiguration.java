package com.algomau.bank.config;

import com.algomau.bank.domain.repository.AccountRepository;
import com.algomau.bank.domain.repository.TransactionRepository;
import com.algomau.bank.domain.repository.UserAccountRepository;
import com.algomau.bank.domain.repository.UserRepository;
import com.algomau.bank.service.AccountService;
import com.algomau.bank.service.TransactionService;
import com.algomau.bank.service.UserAccountService;
import com.algomau.bank.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BankConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public AccountService accountService(AccountRepository accountRepository, ModelMapper modelMapper) {
        return new AccountService(accountRepository, modelMapper);
    }

    @Bean
    public TransactionService transactionService(TransactionRepository transactionRepository, ModelMapper modelMapper) {
        return new TransactionService(transactionRepository, modelMapper);
    }

    @Bean
    public UserAccountService userAccountService(UserAccountRepository userAccountRepository, ModelMapper modelMapper, @Lazy PasswordEncoder passwordEncoder) {
        return new UserAccountService(userAccountRepository, modelMapper, passwordEncoder);
    }

    @Bean
    public UserService userService(UserRepository userRepository, ModelMapper modelMapper) {
        return new UserService(userRepository, modelMapper);
    }

}

package com.algomau.bank.config;

import com.algomau.bank.domain.repository.*;
import com.algomau.bank.dto.response.TransactionResponseDto;
import com.algomau.bank.service.*;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableMethodSecurity
public class BankConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(TransactionResponseDto.class, TransactionResponseDto.class );
        return modelMapper;
    }

    @Bean
    public AccountService accountService(AccountRepository accountRepository, ModelMapper modelMapper, UserRepository userRepository, BankRepository bankRepository) {
        return new AccountService(accountRepository, modelMapper, userRepository, bankRepository);
    }

    @Bean
    public TransactionService transactionService(TransactionRepository transactionRepository, ModelMapper modelMapper, UserAccountService userAccountService, AccountRepository accountRepository) {
        return new TransactionService(transactionRepository, modelMapper, userAccountService, accountRepository);
    }

    @Bean
    public UserAccountService userAccountService(UserAccountRepository userAccountRepository, ModelMapper modelMapper, @Lazy PasswordEncoder passwordEncoder) {
        return new UserAccountService(userAccountRepository, modelMapper, passwordEncoder);
    }

    @Bean
    public UserService userService(UserRepository userRepository, ModelMapper modelMapper, UserAccountService userAccountService) {
        return new UserService(userRepository, modelMapper, userAccountService);
    }

    @Bean
    public BankService bankService(BankRepository bankRepository, ModelMapper modelMapper) {
        return new BankService(bankRepository, modelMapper);
    }

    @Bean
    public FundsService fundsService(TransactionService transactionService, ModelMapper modelMapper)  {
        return new FundsService(transactionService, modelMapper);
    }

}

package com.spring.reactive.loan.repository.test;

import com.spring.reactive.loan.entity.LoanEntity;
import com.spring.reactive.loan.repository.LoanRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import javax.swing.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@DataMongoTest
@ExtendWith(SpringExtension.class)
public class LoanRepositoryTest {

    @Autowired
    private LoanRepository loanRepository;
    LocalDate s1 = LocalDate.of(2020,12,05);






    @Test
    public void saveTest(){
        LoanEntity newLoan = new LoanEntity("1","home",445555.00,s1,4.0,4.0);

        Mono<LoanEntity> saveLoan = loanRepository.save(newLoan);
        StepVerifier.create(saveLoan)
                .expectSubscription()
                .expectNextMatches(loan->loan.getUserId().equalsIgnoreCase("1")&& loan.getLoanType().equalsIgnoreCase("home"))
                .verifyComplete();

    }
    @Test
    public void getItemByID() {
        LoanEntity newLoan = new LoanEntity("1","home",445555.00,s1,4.0,4.0);

        Mono<LoanEntity> saveLoan = loanRepository.save(newLoan);
        StepVerifier.create(loanRepository.findByUserId("1"))
                .expectSubscription()
                .expectNextMatches((item -> item.getLoanType().equals("home")))
                .verifyComplete();


    }

}

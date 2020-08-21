package com.spring.reactive.loan.handler.test;

import com.spring.reactive.loan.entity.LoanEntity;
import com.spring.reactive.loan.repository.LoanRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext
@AutoConfigureWebTestClient
public class LoanHandlerTest {

    @Autowired
    WebTestClient webTestClient;

    @Autowired
    LoanRepository loanRepository;


    LocalDate s1 = LocalDate.of(2020,12,05);
    LocalDate s2 = LocalDate.of(2021,05,06);
    LocalDate s3 = LocalDate.of(2022,11,10);
    public  List<LoanEntity> data() {

    return Arrays.asList(
            new LoanEntity("1","home",445555.00,s1,4.0,4.0),
            new LoanEntity("2","Education",445555.00,s2,4.0,4.0),
            new LoanEntity("3","car",445555.00,s3,4.0,4.0)

    );


    }


 @BeforeEach
    public void setUp(){
        loanRepository.deleteAll()
                .thenMany(Flux.fromIterable(data()))
                .flatMap(loanRepository::save)
                .doOnNext((item -> {
                    System.out.println("Inserted loan is : " + item);
                }))
                .blockLast();
 }

    @Test
    public void getOneItem(){

        webTestClient.get().uri("/v1/function/getall/{userId}","3")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.loanType", "car");

    }
    @Test
    public void saveLoan(){

        webTestClient.post().uri("/v1/function/loan")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.loanType", "home");

    }


}

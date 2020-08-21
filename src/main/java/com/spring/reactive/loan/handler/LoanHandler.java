package com.spring.reactive.loan.handler;

import com.spring.reactive.loan.entity.ExceptionResponse;
import com.spring.reactive.loan.entity.LoanEntity;
import com.spring.reactive.loan.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.awt.*;
import java.util.Collections;
import java.util.Date;

@Component
public class LoanHandler {
    @Autowired
    LoanRepository loanRepository;
    static Mono<ServerResponse> notFound = ServerResponse.notFound().build();

    public Mono<ServerResponse> applyLoan(@Valid  ServerRequest serverRequest) {

        Mono<LoanEntity> loanDetails = serverRequest.bodyToMono(LoanEntity.class);
        return loanDetails.flatMap(loan -> ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(loanRepository.save(loan), LoanEntity.class).switchIfEmpty(notFound)
        );

    }
    public Mono<ServerResponse> getLoan(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("userId");
        Flux<LoanEntity> getloan = loanRepository.findByUserId(id);

    return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(getloan, LoanEntity.class);


}
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Validation Failed",
                ex.getBindingResult().toString());
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }


}

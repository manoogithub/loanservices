package com.spring.reactive.loan.router;

import com.spring.reactive.loan.handler.LoanHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class LoanRouter {

    @Bean
    public RouterFunction<ServerResponse> loanRouters(LoanHandler loanHandler){


        return RouterFunctions.route(POST("/v1/function/loan").and(accept(MediaType.APPLICATION_JSON)),loanHandler::applyLoan)
                .andRoute(GET("/v1/function/getall/{userId}").and(accept(MediaType.APPLICATION_JSON)),loanHandler::getLoan);


    }
}

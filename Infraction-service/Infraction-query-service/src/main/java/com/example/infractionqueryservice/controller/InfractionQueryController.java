package com.example.infractionqueryservice.controller;


import com.example.infractionqueryservice.services.InfractionQueryHandler;
import coreapi.GetInfractionsByNationalCardNumber;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/query")
@Slf4j
@CrossOrigin("*")
public class InfractionQueryController {
    private QueryGateway queryGateway;
    InfractionQueryHandler infractionQueryHandler;

    public InfractionQueryController(QueryGateway queryGateway,InfractionQueryHandler infractionQueryHandler) {

        this.queryGateway = queryGateway;
        this.infractionQueryHandler = infractionQueryHandler;
    }


    @GetMapping("/infactions")
    public CompletableFuture<Page>  infractionByNationalCardNumber(
                                                                     @RequestParam(name = "page",defaultValue = "0") int page,
                                                                     @RequestParam(name = "size",defaultValue = "10") int size,
                                                                     @RequestParam(name = "ncid") String natCardNumber) throws ExecutionException, InterruptedException {

        return queryGateway.query(
                new GetInfractionsByNationalCardNumber(natCardNumber,page,size),
                ResponseTypes.instanceOf(Page.class)
        );

    }
    @GetMapping("/allInfactions")
    public Page  getinfractionByNationalCardNumber(
            @RequestParam(name = "page",defaultValue = "0") int page,
            @RequestParam(name = "size",defaultValue = "10") int size,
            @RequestParam(name = "ncid") String natCardNumber) throws ExecutionException, InterruptedException {

        return infractionQueryHandler.getinfractions(new GetInfractionsByNationalCardNumber(natCardNumber,page,size));

    }
}

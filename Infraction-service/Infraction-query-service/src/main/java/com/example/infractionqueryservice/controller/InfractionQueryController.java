package com.example.infractionqueryservice.controller;


import coreapi.GetInfractionsByNationalCardNumber;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/query")
@CrossOrigin("*")
public class InfractionQueryController {
    private QueryGateway queryGateway;

    public InfractionQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }


    @GetMapping("/infactions")
    public Page infractionByNationalCardNumber(
                                                                     @RequestParam(name = "page",defaultValue = "0") int page,
                                                                     @RequestParam(name = "size",defaultValue = "10") int size,
                                                                     @RequestParam(name = "ncid") String natCardNumber) throws ExecutionException, InterruptedException {

        CompletableFuture<Page> v = queryGateway.query(
                new GetInfractionsByNationalCardNumber(natCardNumber,page,size),
                ResponseTypes.instanceOf(Page.class)
        );
        return v.get();
    }
}

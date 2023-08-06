package com.seerbit.transaction.controller;

import com.seerbit.transaction.constant.ApiRoute;
import com.seerbit.transaction.dto.request.TransactionRequest;
import com.seerbit.transaction.dto.response.TransactionStatisticsDto;
import com.seerbit.transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(ApiRoute.TRANSACTIONS)
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService service;

    @PostMapping
    public ResponseEntity<Object> saveTransaction(@RequestBody @Valid TransactionRequest request){
        service.saveTransaction(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(ApiRoute.STATISTICS)
    public ResponseEntity<TransactionStatisticsDto> getStatistics(){
        return ResponseEntity.ok(service.getTransactionStatistics());
    }

    @DeleteMapping
    public ResponseEntity<Object> clearTransactions(){
        service.clearTransactions();
        return ResponseEntity.noContent().build();
    }
}

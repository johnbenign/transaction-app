package com.seerbit.transaction.mapper;

import com.seerbit.transaction.dto.request.TransactionRequest;
import com.seerbit.transaction.model.Transaction;

import java.math.BigDecimal;
import java.time.Instant;

public class TransactionMapper {
    private TransactionMapper(){}

    public static Transaction mapToEntity(TransactionRequest request){
        if(request == null) return null;

        Transaction entity = new Transaction();
        entity.setAmount(new BigDecimal(request.getAmount()));
        entity.setTimestamp(Instant.parse(request.getTimestamp()));

        return entity;
    }

//    public static Function<TransactionRequest, Transaction> mapToEntity = request -> {
//        if(request == null) return null;
//
//        Transaction entity = new Transaction();
//        entity.setAmount(request.getAmount());
//        entity.setTimestamp(request.getTimestamp());
//
//        return entity;
//    };
}

package com.seerbit.transaction.service.impl;

import com.seerbit.transaction.constant.MessageConstant;
import com.seerbit.transaction.dto.request.TransactionRequest;
import com.seerbit.transaction.dto.response.TransactionStatisticsDto;
import com.seerbit.transaction.exception.TimeOutOfBoundsException;
import com.seerbit.transaction.mapper.TransactionMapper;
import com.seerbit.transaction.model.Transaction;
import com.seerbit.transaction.service.StatisticsService;
import com.seerbit.transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.Synchronized;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final StatisticsService statisticsService;
    private List<Transaction> transactions = new ArrayList<>();

    @Override
    @Synchronized
    public void saveTransaction(TransactionRequest request){
        //check if transaction is within the stipulated duration
        Instant requestedDate = Instant.parse(request.getTimestamp());
        //check if date is in the future
        boolean isFuture = validateIfFutureDate(requestedDate);
        if(isFuture)
            throw new ConstraintViolationException(MessageConstant.TIME_VIOLATION, null);
        boolean isWithinDuration = checkIfTransactionIsWithinDuration(requestedDate);
        if(!isWithinDuration)
            throw new TimeOutOfBoundsException();
        //mapping the transactions to entity
        Transaction transaction = TransactionMapper.mapToEntity(request);
        //save the transaction
        transactions.add(transaction);
    }

    private boolean validateIfFutureDate(Instant requestedDate) {
        return requestedDate.isAfter(Instant.now());
    }

    @Override
    @Synchronized
    public TransactionStatisticsDto getTransactionStatistics(){
        //get transaction statistics
        return statisticsService.getStatistics(transactions);
    }

    @Override
    @Synchronized
    public void clearTransactions(){
        transactions.clear();
    }

    private boolean checkIfTransactionIsWithinDuration(Instant timestamp) {
        return timestamp.isAfter(Instant.now().minusSeconds(30L));
    }
}

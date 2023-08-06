package com.seerbit.transaction.service.impl;

import com.seerbit.transaction.dto.response.TransactionStatisticsDto;
import com.seerbit.transaction.model.Transaction;
import com.seerbit.transaction.service.StatisticsService;
import lombok.Synchronized;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.List;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Override
    @Synchronized
    public TransactionStatisticsDto getStatistics(List<Transaction> transactions){

        //initialize the statistics variables
        BigDecimal sum = BigDecimal.ZERO;
        BigDecimal max = BigDecimal.ZERO;
        BigDecimal min = null;
        long count = 0;

        //loop and calculate each variable
        for(Transaction tx : transactions){
            boolean isWithinDuration = tx.getTimestamp().isAfter(Instant.now().minusSeconds(30));
            if(isWithinDuration){
                sum = sum.add(tx.getAmount());
                if(max.compareTo(tx.getAmount()) < 0)
                    max = tx.getAmount();
                if (min == null || min.compareTo(tx.getAmount()) > 0)
                    min = tx.getAmount();
                count++;
            }
        }

        //incase if min remains null
        if(min == null)
            min = BigDecimal.ZERO;

        //build the response
        return TransactionStatisticsDto.builder()
                .sum(sum.setScale(2, RoundingMode.HALF_UP))
                .min(min.setScale(2, RoundingMode.HALF_UP))
                .avg(count == 0 ? BigDecimal.ZERO : sum.divide(BigDecimal.valueOf(count), 2, RoundingMode.HALF_UP))
                .max(max.setScale(2, RoundingMode.HALF_UP))
                .count(count)
                .build();
    }


}

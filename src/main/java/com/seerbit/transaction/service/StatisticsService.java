package com.seerbit.transaction.service;

import com.seerbit.transaction.dto.response.TransactionStatisticsDto;
import com.seerbit.transaction.model.Transaction;
import java.util.List;

public interface StatisticsService {
    TransactionStatisticsDto getStatistics(List<Transaction> transactions);
}

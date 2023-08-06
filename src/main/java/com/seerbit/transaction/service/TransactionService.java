package com.seerbit.transaction.service;

import com.seerbit.transaction.dto.request.TransactionRequest;
import com.seerbit.transaction.dto.response.TransactionStatisticsDto;

public interface TransactionService {
    void saveTransaction(TransactionRequest request);

    TransactionStatisticsDto getTransactionStatistics();

    void clearTransactions();
}

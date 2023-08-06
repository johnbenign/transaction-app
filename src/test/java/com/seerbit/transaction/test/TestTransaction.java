package com.seerbit.transaction.test;

import com.seerbit.transaction.controller.TransactionController;
import com.seerbit.transaction.dto.request.TransactionRequest;
import com.seerbit.transaction.dto.response.TransactionStatisticsDto;
import com.seerbit.transaction.service.TransactionService;
import java.lang.Object;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
final class TestTransaction{
  private TransactionController transactionController;

  private TransactionService service;

  @BeforeEach
  public void setup(){
    service = Mockito.mock(TransactionService.class);
    transactionController = new TransactionController(service);
  }

  @Test
  void testMethodSaveTransaction() {
    TransactionRequest request = new TransactionRequest();

    request.setAmount("55");
    request.setTimestamp("2023-08-06T18:46:30.212");

    ResponseEntity<Object> responseEntity = transactionController.saveTransaction(request);
    ResponseEntity<Object> responseEntityExpected = ResponseEntity.status(HttpStatus.CREATED).build();
    System.out.println("responseEntityExpected.getStatusCodeValue()::" + responseEntityExpected.getStatusCodeValue() );
    System.out.println("responseEntity.getStatusCodeValue():: "+ responseEntity.getStatusCodeValue());
    Assertions.assertEquals(responseEntityExpected.getStatusCodeValue(), responseEntity.getStatusCodeValue());
  }

  @Test
  void testMethodGetStatistics() {
    ResponseEntity<TransactionStatisticsDto> responseEntity = transactionController.getStatistics();
    ResponseEntity<TransactionStatisticsDto> responseEntityExpected = new ResponseEntity<>(HttpStatus.OK);
    Assertions.assertEquals(responseEntityExpected.getStatusCodeValue(), responseEntity.getStatusCodeValue());
  }

  @Test
  void testMethodClearTransactions() {
    ResponseEntity<Object> responseEntity = transactionController.clearTransactions();
    ResponseEntity<Object> responseEntityExpected = ResponseEntity.noContent().build();
    Assertions.assertEquals(responseEntityExpected.getStatusCodeValue(), responseEntity.getStatusCodeValue());
  }

}

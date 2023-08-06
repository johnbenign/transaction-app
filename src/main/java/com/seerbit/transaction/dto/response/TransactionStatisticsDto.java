package com.seerbit.transaction.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class TransactionStatisticsDto {
    private BigDecimal sum;
    private BigDecimal avg;
    private BigDecimal min;
    private BigDecimal max;
    private long count;
}

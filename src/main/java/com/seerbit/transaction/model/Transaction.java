package com.seerbit.transaction.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZonedDateTime;

@Getter
@Setter
public class Transaction {
    private BigDecimal amount;
    private Instant timestamp;
}

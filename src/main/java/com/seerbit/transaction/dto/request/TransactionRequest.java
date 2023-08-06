package com.seerbit.transaction.dto.request;

import com.seerbit.transaction.constant.MessageConstant;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TransactionRequest {
    @Positive(message = MessageConstant.INVALID_AMOUNT)
    @NotNull(message = MessageConstant.AMOUNT_REQUIRED)
    private String amount;

    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3}Z", message = MessageConstant.UNSUPPORTED_TIME_FORMAT)
    private String timestamp;
}

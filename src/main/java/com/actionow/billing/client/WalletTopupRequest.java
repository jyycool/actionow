package com.actionow.billing.client;

import lombok.Data;

/**
 * 钱包充值请求（内部）
 */
@Data
public class WalletTopupRequest {
    private Long amount;
    private String description;
    private String paymentOrderId;
    private String paymentMethod;
}

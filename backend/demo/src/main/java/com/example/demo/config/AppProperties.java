package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppProperties {

    @Value("${app.trafficType}")
    public String trafficType;

    @Value("${app.operation.createBasket}")
    public String createBasketOp;

    @Value("${app.operation.cancelBasket}")
    public String cancelBasketOp;

    @Value("${app.error.productNotFound}")
    public String productNotFoundMsg;

    @Value("${app.error.cartItemNotFound}")
    public String cartItemNotFoundMsg;
}

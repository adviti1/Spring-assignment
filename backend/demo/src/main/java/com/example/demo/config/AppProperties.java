package com.example.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app")
public class AppProperties {

    private Operation operation;

    public static class Operation {
        private String createBasket;

        public String getCreateBasket() {
            return createBasket;
        }

        public void setCreateBasket(String createBasket) {
            this.createBasket = createBasket;
        }
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }
}

package com.rasysbox.shopping.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Customer {
    private Long id;
    private String name;
    private String state;
}

package com.rasysbox.shopping.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Country {
    private Long id;
    private String country;
    private String state;
}

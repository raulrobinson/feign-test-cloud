package com.rasysbox.customer.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Data
@Table(name = "tbl_customer")
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String state;
}

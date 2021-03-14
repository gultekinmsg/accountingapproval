package com.emlakjet.accountingapproval.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Integer amount;
    private String productName;
    private String billNo;
    @Enumerated(EnumType.STRING)
    private BillStatus billStatus;
}

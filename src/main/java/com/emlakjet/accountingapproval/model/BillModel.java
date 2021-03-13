package com.emlakjet.accountingapproval.model;

import lombok.Data;

@Data
public class BillModel {
    private String firstName;
    private String lastName;
    private String email;
    private Integer amount;
    private String productName;
    private String billNo;
}

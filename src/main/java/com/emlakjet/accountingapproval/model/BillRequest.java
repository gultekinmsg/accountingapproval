package com.emlakjet.accountingapproval.model;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
public class BillRequest {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @Email
    private String email;
    @NotNull
    private Integer amount;
    @NotBlank
    private String productName;
    @NotBlank
    private String billNo;
}

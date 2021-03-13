package com.emlakjet.accountingapproval.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BillRequest {
    @NotBlank
    @Size(max = 255)
    private String firstName;
    @NotBlank
    @Size(max = 255)
    private String lastName;
    @NotBlank
    @Email
    private String email;
    @NotNull
    @Min(0)
    private Integer amount;
    @NotBlank
    @Size(max = 255)
    private String productName;
    @NotBlank
    @Size(max = 255)
    private String billNo;
}

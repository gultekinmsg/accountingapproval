package com.emlakjet.accountingapproval.model;

import com.emlakjet.accountingapproval.entity.BillStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillStatusResponse {
    private BillStatus status;
}

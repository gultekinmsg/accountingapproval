package com.emlakjet.accountingapproval.controller;

import com.emlakjet.accountingapproval.entity.BillStatus;
import com.emlakjet.accountingapproval.model.BillModel;
import com.emlakjet.accountingapproval.model.BillRequest;
import com.emlakjet.accountingapproval.model.BillStatusResponse;
import com.emlakjet.accountingapproval.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class BillController {
    private final BillService billService;

    @Autowired
    public BillController(BillService billService) {
        this.billService = billService;
    }

    @GetMapping
    public List<BillModel> getBills(@RequestParam(name = "status") BillStatus status) {
        if (status == BillStatus.APPROVED) {
            return billService.getApprovedBills();
        }
        return billService.getDeniedBills();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public BillStatusResponse addBill(@RequestBody @Valid BillRequest billRequest) {
        return new BillStatusResponse(billService.addBill(billRequest));
    }
}

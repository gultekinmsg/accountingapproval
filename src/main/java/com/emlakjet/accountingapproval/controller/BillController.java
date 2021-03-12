package com.emlakjet.accountingapproval.controller;

import com.emlakjet.accountingapproval.entity.BillStatus;
import com.emlakjet.accountingapproval.model.BillRequest;
import com.emlakjet.accountingapproval.model.BillResponse;
import com.emlakjet.accountingapproval.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.util.List;

@RestController
public class BillController {
    private final BillService billService;

    @Autowired
    public BillController(BillService billService) {
        this.billService = billService;
    }

    @GetMapping("/Approved")
    public List<BillResponse> getApprovedLinks() {
        return billService.getApprovedBills();
    }

    @GetMapping("/Denied")
    public List<BillResponse> getDeniedLinks() {
        return billService.getDeniedBills();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/Process")
    public BillStatus addBill(@RequestBody @Valid BillRequest billRequest) throws FileNotFoundException {
        return billService.addBill(billRequest);
    }
}

package com.emlakjet.accountingapproval.util;

import com.emlakjet.accountingapproval.entity.Bill;
import com.emlakjet.accountingapproval.entity.BillStatus;
import com.emlakjet.accountingapproval.model.BillRequest;
import com.emlakjet.accountingapproval.model.BillResponse;

import java.util.ArrayList;
import java.util.List;

public class BillMapper {

    private BillMapper() {
        throw new IllegalStateException("Utility BillMapper Class");
    }

    public static List<BillResponse> allToModels(List<Bill> billList) {
        List<BillResponse> billResponseList = new ArrayList<>();
        billList.forEach(bill -> billResponseList.add(toModel(bill)));
        return billResponseList;
    }

    public static BillResponse toModel(Bill bill) {
        BillResponse billResponse = new BillResponse();
        billResponse.setFirstName(bill.getFirstName());
        billResponse.setLastName(bill.getLastName());
        billResponse.setEmail(bill.getEmail());
        billResponse.setAmount(bill.getAmount());
        billResponse.setProductName(bill.getProductName());
        billResponse.setBillNo(bill.getBillNo());
        return billResponse;
    }

    public static Bill toEntity(BillRequest billRequest, BillStatus billStatus) {
        Bill bill = new Bill();
        bill.setFirstName(billRequest.getFirstName());
        bill.setLastName(billRequest.getLastName());
        bill.setEmail(billRequest.getEmail());
        bill.setAmount(billRequest.getAmount());
        bill.setProductName(billRequest.getProductName());
        bill.setBillNo(billRequest.getBillNo());
        bill.setBillStatus(billStatus);
        return bill;
    }
}

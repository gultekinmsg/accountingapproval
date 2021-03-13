package com.emlakjet.accountingapproval.util;

import com.emlakjet.accountingapproval.entity.Bill;
import com.emlakjet.accountingapproval.entity.BillStatus;
import com.emlakjet.accountingapproval.model.BillRequest;
import com.emlakjet.accountingapproval.model.BillModel;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class BillMapper {

    private BillMapper() {
    }

    public static List<BillModel> allToModels(List<Bill> billList) {
        List<BillModel> billModelList = new ArrayList<>();
        billList.forEach(bill -> billModelList.add(toModel(bill)));
        return billModelList;
    }

    public static BillModel toModel(Bill bill) {
        BillModel billModel = new BillModel();
        BeanUtils.copyProperties(bill, billModel);
        return billModel;
    }

    public static Bill toEntity(BillRequest billRequest, BillStatus billStatus) {
        Bill bill = new Bill();
        BeanUtils.copyProperties(billRequest,bill);
        bill.setBillStatus(billStatus);
        return bill;
    }
}

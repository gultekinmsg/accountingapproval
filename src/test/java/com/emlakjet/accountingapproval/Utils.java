package com.emlakjet.accountingapproval;

import com.emlakjet.accountingapproval.entity.Bill;
import com.emlakjet.accountingapproval.entity.BillStatus;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    private Utils() {
    }

    protected static List<Bill> getDbDeniedList() {
        List<Bill> deniedBillList = new ArrayList<>();
        deniedBillList.add(setDeniedBill());
        return deniedBillList;
    }

    protected static Bill setDeniedBill() {
        Bill bill = new Bill();
        bill.setFirstName("Muhammed");
        bill.setLastName("Gultekin");
        bill.setEmail("gultekinmsg@gmail.com");
        bill.setAmount(205);
        bill.setProductName("USB");
        bill.setBillNo("TR0");
        bill.setBillStatus(BillStatus.DENIED);
        return bill;
    }

    protected static List<Bill> getDbApprovedList() {
        List<Bill> approvedBillList = new ArrayList<>();
        approvedBillList.add(setApprovedBill());
        return approvedBillList;
    }

    protected static Bill setApprovedBill() {
        Bill bill = new Bill();
        bill.setFirstName("Muhammed");
        bill.setLastName("Gultekin");
        bill.setEmail("gultekinmsg@gmail.com");
        bill.setAmount(150);
        bill.setProductName("USB");
        bill.setBillNo("TR0");
        bill.setBillStatus(BillStatus.APPROVED);
        return bill;
    }
}

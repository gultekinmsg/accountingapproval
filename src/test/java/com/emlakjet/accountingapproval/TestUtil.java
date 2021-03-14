package com.emlakjet.accountingapproval;

import com.emlakjet.accountingapproval.entity.Bill;
import com.emlakjet.accountingapproval.entity.BillStatus;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class TestUtil {
    private TestUtil() {
    }

    protected static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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

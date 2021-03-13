package com.emlakjet.accountingapproval;

import com.emlakjet.accountingapproval.controller.BillController;
import com.emlakjet.accountingapproval.entity.BillStatus;
import com.emlakjet.accountingapproval.model.BillModel;
import com.emlakjet.accountingapproval.model.BillRequest;
import com.emlakjet.accountingapproval.service.BillService;
import com.emlakjet.accountingapproval.util.BillMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class BillControllerTest {

    @MockBean
    BillService billService;

    @Autowired
    BillController billController;

    @Test
    void getApprovedBills() {
        when(billService.getApprovedBills()).thenReturn(BillMapper.allToModels(Utils.getDbApprovedList()));
        List<BillModel> billModels = billController.getBills(BillStatus.APPROVED);
        Assertions.assertEquals(BillMapper.allToModels(Utils.getDbApprovedList()), billModels);
    }

    @Test
    void getDeniedBills() {
        when(billService.getDeniedBills()).thenReturn(BillMapper.allToModels(Utils.getDbDeniedList()));
        List<BillModel> billModels = billController.getBills(BillStatus.DENIED);
        Assertions.assertEquals(BillMapper.allToModels(Utils.getDbDeniedList()), billModels);
    }

    @Test
    void addApprovedBill() {
        BillRequest billRequest = new BillRequest("Muhammed", "Gultekin", "gultekinmsg@gmail.com", 75, "usb", "tr01");
        when(billService.addBill(billRequest)).thenReturn(BillStatus.APPROVED);
        BillStatus billStatus = billService.addBill(billRequest);
        assertEquals(BillStatus.APPROVED, billStatus);
    }

    @Test
    void addDeniedBill() {
        BillRequest billRequest = new BillRequest("Muhammed", "Gultekin", "gultekinmsg@gmail.com", 201, "usb", "tr01");
        when(billService.addBill(billRequest)).thenReturn(BillStatus.DENIED);
        BillStatus billStatus = billService.addBill(billRequest);
        assertEquals(BillStatus.DENIED, billStatus);
    }
}
package com.emlakjet.accountingapproval;

import com.emlakjet.accountingapproval.entity.BillStatus;
import com.emlakjet.accountingapproval.model.BillModel;
import com.emlakjet.accountingapproval.model.BillRequest;
import com.emlakjet.accountingapproval.repository.BillRepository;
import com.emlakjet.accountingapproval.service.BillService;
import com.emlakjet.accountingapproval.util.BillMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class BillServiceTest {

    @MockBean
    private BillRepository billRepository;

    @Autowired
    private BillService billService;

    @Test
    void addBill_GivenRequestHasNotApprovedRequestInDbAndAmountIsValidWhenTryToAddBillThenShouldApproveBill() {
        BillRequest billRequest = new BillRequest("Muhammed", "Gultekin", "gultekinmsg@gmail.com", 75, "usb", "tr01");
        when(billRepository.findByEmailAndBillStatus(billRequest.getEmail(), BillStatus.APPROVED)).thenReturn(new ArrayList<>());
        BillStatus billStatus = billService.addBill(billRequest);
        assertEquals(BillStatus.APPROVED, billStatus);
    }

    @Test
    void addBill_GivenRequestAmountIsNotValidWhenTryToAddBillThenShoulDeniedBill() {
        BillRequest billRequest = new BillRequest("Muhammed", "Gultekin", "gultekinmsg@gmail.com", 201, "usb", "tr01");
        BillStatus billStatus = billService.addBill(billRequest);
        assertEquals(BillStatus.DENIED, billStatus);
    }

    @Test
    void addBill_GivenRequestHasApprovedRequestsInDbAndTotalAmountIsNOTValidWhenTryToAddBllThenShouldDeniedBill() {
        BillRequest billRequest = new BillRequest("Muhammed", "Gultekin", "gultekinmsg@gmail.com", 51, "usb", "tr01");
        when(billRepository.findByEmailAndBillStatus(billRequest.getEmail(), BillStatus.APPROVED)).thenReturn(Utils.getDbApprovedList());
        BillStatus billStatus = billService.addBill(billRequest);
        assertEquals(BillStatus.DENIED, billStatus);
    }

    @Test
    void addBill_GivenRequestHasApprovedRequestsInDbAndTotalAmountIsValidWhenTryToAddBllThenShouldApproveBill() {
        BillRequest billRequest = new BillRequest("Muhammed", "Gultekin", "gultekinmsg@gmail.com", 40, "usb", "tr01");
        when(billRepository.findByEmailAndBillStatus(billRequest.getEmail(), BillStatus.APPROVED)).thenReturn(Utils.getDbApprovedList());
        BillStatus billStatus = billService.addBill(billRequest);
        assertEquals(BillStatus.APPROVED, billStatus);
    }

    @Test
    void getApprovedBills_ApprovedBillsIsExistInDbWhenTryToGetApprovedBillsThenShouldReturnApprovedBillsList() {
        when(billRepository.findByBillStatus(BillStatus.APPROVED)).thenReturn(Utils.getDbApprovedList());
        List<BillModel> billModels = billService.getApprovedBills();
        assertEquals(billModels, BillMapper.allToModels(Utils.getDbApprovedList()));
    }

    @Test
    void getDeniedBills_DeniedBillsIsExistInDbWhenTryToGetDeniedBillsThenShouldReturnDeniedBillsList() {
        when(billRepository.findByBillStatus(BillStatus.DENIED)).thenReturn(Utils.getDbDeniedList());
        List<BillModel> billModels = billService.getDeniedBills();
        assertEquals(billModels, BillMapper.allToModels(Utils.getDbDeniedList()));
    }

}

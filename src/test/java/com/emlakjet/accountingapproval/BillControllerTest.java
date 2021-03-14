package com.emlakjet.accountingapproval;

import com.emlakjet.accountingapproval.entity.BillStatus;
import com.emlakjet.accountingapproval.model.BillModel;
import com.emlakjet.accountingapproval.model.BillRequest;
import com.emlakjet.accountingapproval.service.BillService;
import com.emlakjet.accountingapproval.util.BillMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BillControllerTest {

    @MockBean
    BillService billService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void getApprovedBills() throws Exception {
        List<BillModel> billModels = BillMapper.allToModels(TestUtil.getDbApprovedList());
        when(billService.getApprovedBills()).thenReturn(billModels);

        mockMvc.perform(get("/?status=APPROVED"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(billModels.size())))
                .andExpect(jsonPath("$[0].firstName").value(billModels.get(0).getFirstName()))
                .andExpect(jsonPath("$[0].lastName").value(billModels.get(0).getLastName()))
                .andExpect(jsonPath("$[0].email").value(billModels.get(0).getEmail()))
                .andExpect(jsonPath("$[0].amount").value(billModels.get(0).getAmount()))
                .andExpect(jsonPath("$[0].productName").value(billModels.get(0).getProductName()))
                .andExpect(jsonPath("$[0].billNo").value(billModels.get(0).getBillNo()));
    }

    @Test
    void getDeniedBills() throws Exception {
        List<BillModel> billModels = BillMapper.allToModels(TestUtil.getDbDeniedList());
        when(billService.getDeniedBills()).thenReturn(billModels);
        mockMvc.perform(get("/?status=DENIED"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(billModels.size())))
                .andExpect(jsonPath("$[0].firstName").value(billModels.get(0).getFirstName()))
                .andExpect(jsonPath("$[0].lastName").value(billModels.get(0).getLastName()))
                .andExpect(jsonPath("$[0].email").value(billModels.get(0).getEmail()))
                .andExpect(jsonPath("$[0].amount").value(billModels.get(0).getAmount()))
                .andExpect(jsonPath("$[0].productName").value(billModels.get(0).getProductName()))
                .andExpect(jsonPath("$[0].billNo").value(billModels.get(0).getBillNo()));
    }

    @Test
    void addApprovedBill() throws Exception {
        BillRequest billRequest = new BillRequest("Muhammed", "Gultekin", "gultekinmsg@gmail.com", 75, "usb", "tr01");
        when(billService.addBill(billRequest)).thenReturn(BillStatus.APPROVED);
        mockMvc.perform(post("/").content(TestUtil.asJsonString(billRequest)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value(BillStatus.APPROVED.toString()));
    }

    @Test
    void addDeniedBill() throws Exception {
        BillRequest billRequest = new BillRequest("Muhammed", "Gultekin", "gultekinmsg@gmail.com", 201, "usb", "tr01");
        when(billService.addBill(billRequest)).thenReturn(BillStatus.DENIED);
        mockMvc.perform(post("/").content(TestUtil.asJsonString(billRequest)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value(BillStatus.DENIED.toString()));
    }
}

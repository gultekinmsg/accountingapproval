package com.emlakjet.accountingapproval.service;

import com.emlakjet.accountingapproval.entity.Bill;
import com.emlakjet.accountingapproval.entity.BillStatus;
import com.emlakjet.accountingapproval.model.BillModel;
import com.emlakjet.accountingapproval.model.BillRequest;
import com.emlakjet.accountingapproval.repository.BillRepository;
import com.emlakjet.accountingapproval.util.BillMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BillService {
    private final BillRepository billRepository;

    @Value("${maxAmountLimit}")
    private Integer maxAmountLimit;

    @Autowired
    public BillService(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    public List<BillModel> getApprovedBills() {
        List<Bill> billList = billRepository.findByBillStatus(BillStatus.APPROVED);
        if (billList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is not any approved bill");
        }
        return BillMapper.allToModels(billList);
    }

    public List<BillModel> getDeniedBills() {
        List<Bill> billList = billRepository.findByBillStatus(BillStatus.DENIED);
        if (billList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is not any denied bill");
        }
        return BillMapper.allToModels(billList);
    }

    public BillStatus addBill(BillRequest billRequest) {
        Bill bill = BillMapper.toEntity(billRequest, checkStatus(billRequest));
        billRepository.save(bill);
        return bill.getBillStatus();
    }

    private BillStatus checkStatus(BillRequest billRequest) {
        Integer billAmount = billRequest.getAmount();
        List<Bill> billList = billRepository.findByEmailAndBillStatus(billRequest.getEmail(), BillStatus.APPROVED);
        if (billList.isEmpty() && billAmount <= maxAmountLimit) {
            return BillStatus.APPROVED;
        } else if (billAmount > maxAmountLimit) {
            return BillStatus.DENIED;
        } else {
            Integer totalAmount = billAmount;
            for (Bill amount : billList) {
                totalAmount += amount.getAmount();
            }
            if (totalAmount <= maxAmountLimit) {
                return BillStatus.APPROVED;
            } else {
                return BillStatus.DENIED;
            }

        }
    }
}

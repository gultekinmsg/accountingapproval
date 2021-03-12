package com.emlakjet.accountingapproval.service;

import com.emlakjet.accountingapproval.entity.Bill;
import com.emlakjet.accountingapproval.entity.BillStatus;
import com.emlakjet.accountingapproval.model.BillRequest;
import com.emlakjet.accountingapproval.model.BillResponse;
import com.emlakjet.accountingapproval.repository.BillRepository;
import com.emlakjet.accountingapproval.util.BillMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.yaml.snakeyaml.Yaml;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.List;
import java.util.Map;

@Service
public class BillService {
    private final BillRepository billRepository;

    @Autowired
    public BillService(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    public List<BillResponse> getApprovedBills() {
        List<Bill> billList = billRepository.findByBillStatus(BillStatus.APPROVED);
        if (billList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is not any approved bill");
        }
        return BillMapper.allToModels(billList);
    }

    public List<BillResponse> getDeniedBills() {
        List<Bill> billList = billRepository.findByBillStatus(BillStatus.DENIED);
        if (billList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is not any denied bill");
        }
        return BillMapper.allToModels(billList);
    }

    public BillStatus addBill(BillRequest billRequest) throws FileNotFoundException {
        Bill bill = BillMapper.toEntity(billRequest, checkStatus(billRequest));
        billRepository.save(bill);
        return bill.getBillStatus();
    }

    private BillStatus checkStatus(BillRequest billRequest) throws FileNotFoundException {
        Integer billAmount = billRequest.getAmount();
        List<Bill> billList = billRepository.findByEmailAndBillStatus(billRequest.getEmail(), BillStatus.APPROVED);
        if (billList.isEmpty() && billAmount <= amountLimit()) {
            return BillStatus.APPROVED;
        } else if (billAmount >= amountLimit()) {
            return BillStatus.DENIED;
        } else {
            Integer totalAmount = billAmount;
            for (Bill amount : billList) {
                totalAmount += amount.getAmount();
            }
            if (totalAmount <= amountLimit()) {
                return BillStatus.APPROVED;
            } else {
                return BillStatus.DENIED;
            }

        }
    }

    private Integer amountLimit() throws FileNotFoundException {
        Yaml yaml = new Yaml();
        Reader yamlFile = new FileReader("src/main/resources/application.yml");
        Map<String, Object> yamlMaps = yaml.load(yamlFile);
        if (yamlMaps.get("maxAmountLimit") != null) {
            return (Integer) yamlMaps.get("maxAmountLimit");
        } else {
            return 400;
        }
    }
}

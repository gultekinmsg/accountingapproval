package com.emlakjet.accountingapproval.repository;

import com.emlakjet.accountingapproval.entity.Bill;
import com.emlakjet.accountingapproval.entity.BillStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BillRepository extends CrudRepository<Bill, Long> {
    List<Bill> findByBillStatus(BillStatus billStatus);

    List<Bill> findByEmailAndBillStatus(String email, BillStatus billStatus);
}

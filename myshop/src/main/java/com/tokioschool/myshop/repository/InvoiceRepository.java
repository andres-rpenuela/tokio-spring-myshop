package com.tokioschool.myshop.repository;

import com.tokioschool.myshop.domain.Invoice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface InvoiceRepository extends CrudRepository<Invoice, Long> {

    Invoice findByNumber(String number);
    Set<Invoice> findByUserNif(@Param("user.nif") String nif);
}

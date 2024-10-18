package com.tokioschool.myshop.service;

import com.tokioschool.myshop.domain.Invoice;
import com.tokioschool.myshop.domain.Order;

import java.util.Set;

/**
 * Service para gestión de facturas
 */
public interface InvoiceService {

    Invoice findByNumber(String number);
    Set<Invoice> findByNif(String nif);
    Invoice addInvoice(Invoice invoice);
}

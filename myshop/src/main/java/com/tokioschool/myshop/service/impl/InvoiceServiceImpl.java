package com.tokioschool.myshop.service.impl;

import com.tokioschool.myshop.domain.Invoice;
import com.tokioschool.myshop.repository.InvoiceRepository;
import com.tokioschool.myshop.service.InvoiceService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public Invoice findByNumber(String number) {
        return invoiceRepository.findByNumber(number);
    }

    @Override
    public Set<Invoice> findByNif(String nif) {
        return invoiceRepository.findByUserNif(nif);
    }

	@Override
	public Invoice addInvoice(Invoice invoice) {
		// TODO Auto-generated method stub
		return invoiceRepository.save(invoice);
	}
    
    
}

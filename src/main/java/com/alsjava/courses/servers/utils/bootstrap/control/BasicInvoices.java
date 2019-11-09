package com.alsjava.courses.servers.utils.bootstrap.control;

import com.alsjava.courses.servers.domain.control.Invoice;
import com.alsjava.courses.servers.domain.control.InvoiceDetail;
import com.alsjava.courses.servers.domain.security.Terminal;
import com.alsjava.courses.servers.repository.control.InvoiceRespository;
import com.alsjava.courses.servers.utils.Constants;
import com.alsjava.courses.servers.utils.bootstrap.BootStrapInsert;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by aluis on 11/9/19.
 */
public class BasicInvoices implements BootStrapInsert {

    @Autowired
    private InvoiceRespository invoiceRespository;

    public BasicInvoices() {
        Constants.get().autoWiredClass(this);
    }

    @Override
    public void insert() {

    }

    private Invoice create(Terminal terminal, String serial, LocalDateTime saleDateTime, List<InvoiceDetail> invoiceDetails) {
        Invoice invoice = invoiceRespository.findBySerial(serial);
        if (invoice == null) {
            invoice = new Invoice();
            invoice.setTerminal(terminal);
            invoice.setSerial(serial);
            invoice.setSaleDateTime(saleDateTime);
            invoice.setInvoiceDetails(invoiceDetails);
            return invoiceRespository.saveAndFlush(invoice);
        }
        return invoice;
    }
}

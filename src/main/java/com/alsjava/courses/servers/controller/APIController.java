package com.alsjava.courses.servers.controller;

import com.alsjava.courses.servers.domain.control.Invoice;
import com.alsjava.courses.servers.domain.control.InvoiceDetail;
import com.alsjava.courses.servers.domain.security.Terminal;
import com.alsjava.courses.servers.model.CommunicationConstants;
import com.alsjava.courses.servers.model.api.Report;
import com.alsjava.courses.servers.model.communication.CommunicationCodes;
import com.alsjava.courses.servers.model.communication.request.*;
import com.alsjava.courses.servers.model.communication.response.*;
import com.alsjava.courses.servers.repository.control.InvoiceRespository;
import com.alsjava.courses.servers.repository.control.ProductRespository;
import com.alsjava.courses.servers.repository.security.TerminalRespository;
import com.alsjava.courses.servers.utils.Constants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by aluis on 11/2/19.
 */
@RestController
@RequestMapping("/api")
public class APIController {

    private final TerminalRespository terminalRespository;
    private final ProductRespository productRespository;
    private final InvoiceRespository invoiceRespository;

    public APIController(TerminalRespository terminalRespository, ProductRespository productRespository, InvoiceRespository invoiceRespository) {
        this.terminalRespository = terminalRespository;
        this.productRespository = productRespository;
        this.invoiceRespository = invoiceRespository;
    }

    @RequestMapping(value = "/up", method = RequestMethod.GET)
    public ResponseEntity<String> up() {
        return ResponseEntity.ok("OK");
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<LoginResponse> login(@RequestParam(value = CommunicationConstants.SESSION_FORM_RESOURCE) String session, @RequestParam(value = CommunicationConstants.DATA_FORM_RESOURCE) String request) {
        LoginRequest loginRequest = Constants.get().convert(request, LoginRequest.class);
        if (loginRequest == null) {
            return ResponseEntity.ok(new LoginResponse(CommunicationCodes.SYNTAX_ERROR));
        }
        Terminal terminal = terminalRespository.findByUsernameAndPasswordAndEnabledIsTrue(loginRequest.getUsername(), loginRequest.getPassword());
        if (terminal == null) {
            return ResponseEntity.ok(new LoginResponse(CommunicationCodes.TERMINAL_NO_FOUND));
        }
        String token = Constants.LOGIN_MANAGER.login(terminal.getId());
        LoginResponse loginResponse = new LoginResponse(CommunicationCodes.OK);
        loginResponse.setSession(token);
        loginResponse.setTerminal(terminal.getId());
        return ResponseEntity.ok(new LoginResponse(token, terminal.getId(), terminal.getName()));
    }

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public ResponseEntity<ProductsResponse> products(@RequestParam(value = CommunicationConstants.SESSION_FORM_RESOURCE) String session, @RequestParam(value = CommunicationConstants.DATA_FORM_RESOURCE) String request) {
        if (!Constants.LOGIN_MANAGER.isLogin(session)) {
            return ResponseEntity.ok(new ProductsResponse(CommunicationCodes.NO_ACTIVE_LOGIN));
        }
        ProductsRequest productsRequest = Constants.get().convert(request, ProductsRequest.class);
        if (productsRequest == null) {
            return ResponseEntity.ok(new ProductsResponse(CommunicationCodes.SYNTAX_ERROR));
        }
        return ResponseEntity.ok(new ProductsResponse(productRespository.findAll()));
    }

    @RequestMapping(value = "/invoices", method = RequestMethod.POST)
    public ResponseEntity<InvoicesResponse> invoices(@RequestParam(value = CommunicationConstants.SESSION_FORM_RESOURCE) String session, @RequestParam(value = CommunicationConstants.DATA_FORM_RESOURCE) String request) {
        if (!Constants.LOGIN_MANAGER.isLogin(session)) {
            return ResponseEntity.ok(new InvoicesResponse(CommunicationCodes.NO_ACTIVE_LOGIN));
        }
        InvoicesRequest invoicesRequest = Constants.get().convert(request, InvoicesRequest.class);
        if (invoicesRequest == null) {
            return ResponseEntity.ok(new InvoicesResponse(CommunicationCodes.SYNTAX_ERROR));
        }
        return ResponseEntity.ok(new InvoicesResponse(invoiceRespository.findAll()));
    }

    @RequestMapping(value = "/invoice", method = RequestMethod.POST)
    public ResponseEntity<InvoiceResponse> invoice(@RequestParam(value = CommunicationConstants.SESSION_FORM_RESOURCE) String session, @RequestParam(value = CommunicationConstants.DATA_FORM_RESOURCE) String request) {
        if (!Constants.LOGIN_MANAGER.isLogin(session)) {
            return ResponseEntity.ok(new InvoiceResponse(CommunicationCodes.NO_ACTIVE_LOGIN));
        }
        InvoiceRequest invoiceRequest = Constants.get().convert(request, InvoiceRequest.class);
        if (invoiceRequest == null) {
            return ResponseEntity.ok(new InvoiceResponse(CommunicationCodes.SYNTAX_ERROR));
        }
        try {
            Terminal terminal = terminalRespository.findByIdAndEnabledIsTrue(Constants.LOGIN_MANAGER.getTerminal(session));
            Invoice invoice = new Invoice();
            invoice.setTerminal(terminal);
            invoice.setSaleDateTime(LocalDateTime.now());
            invoice.setSerial(UUID.randomUUID().toString().replaceAll("-", ""));
            List<InvoiceDetail> invoiceDetails = invoiceRequest.getBuys().stream().map(buy -> {
                InvoiceDetail invoiceDetail = new InvoiceDetail();
                invoiceDetail.setProduct(productRespository.findByIdAndEnabledIsTrue(buy.getProduct().getId()));
                invoiceDetail.setQuantity(buy.getQuantity());
                invoiceDetail.setPrice(buy.getPrice());
                return invoiceDetail;
            }).collect(Collectors.toList());
            invoice.setInvoiceDetails(invoiceDetails);
            return ResponseEntity.ok(new InvoiceResponse(invoice.getSerial(), Timestamp.valueOf(invoice.getSaleDateTime())));
        } catch (Exception ex) {
            return ResponseEntity.ok(new InvoiceResponse(CommunicationCodes.INTERNAL_ERROR));
        }
    }

    @RequestMapping(value = "/report", method = RequestMethod.POST)
    public ResponseEntity<ReportResponse> report(@RequestParam(value = CommunicationConstants.SESSION_FORM_RESOURCE) String session, @RequestParam(value = CommunicationConstants.DATA_FORM_RESOURCE) String request) {
        if (!Constants.LOGIN_MANAGER.isLogin(session)) {
            return ResponseEntity.ok(new ReportResponse(CommunicationCodes.NO_ACTIVE_LOGIN));
        }
        ReportRequest invoiceRequest = Constants.get().convert(request, ReportRequest.class);
        if (invoiceRequest == null) {
            return ResponseEntity.ok(new ReportResponse(CommunicationCodes.SYNTAX_ERROR));
        }
        LocalDateTime start = convertToLocalDateTimeViaInstant(invoiceRequest.getStartDate());
        LocalDateTime end = convertToLocalDateTimeViaInstant(invoiceRequest.getEndDate());
        List<Invoice> invoices = invoiceRespository.findAllByEnabledIsTrueAndSaleDateTimeBetween(start, end);
        Report report = new Report();
        report.setInvoiceQuantity(invoices.size());
        double total = invoices.stream().flatMap(invoice -> invoice.getInvoiceDetails().stream()).mapToDouble(invoiceDetail -> (invoiceDetail.getPrice() * invoiceDetail.getQuantity())).sum();
        report.setTotalSales(total);
        return ResponseEntity.ok(new ReportResponse());
    }

    private LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }
}

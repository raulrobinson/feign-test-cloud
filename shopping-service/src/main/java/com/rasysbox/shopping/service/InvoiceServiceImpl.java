package com.rasysbox.shopping.service;

import com.rasysbox.shopping.client.CountryClient;
import com.rasysbox.shopping.client.CustomerClient;
import com.rasysbox.shopping.client.ProductClient;
import com.rasysbox.shopping.entity.InvoiceItem;
import com.rasysbox.shopping.model.Country;
import com.rasysbox.shopping.model.Customer;
import com.rasysbox.shopping.model.Product;
import com.rasysbox.shopping.repository.InvoiceItemsRepository;
import com.rasysbox.shopping.repository.InvoiceRepository;
import com.rasysbox.shopping.entity.Invoice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    InvoiceItemsRepository invoiceItemsRepository;

    @Autowired
    CustomerClient customerClient;

    @Autowired
    CountryClient countryClient;

    @Override
    public List<Invoice> findInvoiceAll() {

        return  invoiceRepository.findAll();
    }

    @Override
    public Invoice createInvoice(Invoice invoice) {
        Invoice invoiceDB = invoiceRepository.findByNumberInvoice ( invoice.getNumberInvoice () );
        if (invoiceDB !=null){
            return  invoiceDB;
        }
        invoice.setState("CREATED");
        invoiceDB = invoiceRepository.save(invoice);
        /*invoiceDB.getItems().forEach( invoiceItem -> {
            productClient.updateStockProduct( invoiceItem.getProductId(), invoiceItem.getQuantity() * -1);
        });*/

        return invoiceDB;
    }

    @Override
    public Invoice updateInvoice(Invoice invoice) {
        Invoice invoiceDB = getInvoice(invoice.getId());
        if (invoiceDB == null){
            return  null;
        }
        invoiceDB.setCustomerId(invoice.getCustomerId());
        invoiceDB.setDescription(invoice.getDescription());
        invoiceDB.setNumberInvoice(invoice.getNumberInvoice());
        invoiceDB.getItems().clear();
        invoiceDB.setItems(invoice.getItems());

        return invoiceRepository.save(invoiceDB);
    }

    @Override
    public Invoice deleteInvoice(Invoice invoice) {
        Invoice invoiceDB = getInvoice(invoice.getId());
        if (invoiceDB == null){
            return  null;
        }
        invoiceDB.setState("DELETED");

        return invoiceRepository.save(invoiceDB);
    }

    @Override
    public Invoice getInvoice(Long id) {
        Invoice invoice= invoiceRepository.findById(id).orElse(null);
        /*if (null != invoice ){
            Customer customer = customerClient.getCustomer(invoice.getCustomerId()).getBody();
            invoice.setCustomer(customer);
            List<InvoiceItem> listItem=invoice.getItems().stream().map(invoiceItem -> {
                Product product = productClient.getProduct(invoiceItem.getProductId()).getBody();
                invoiceItem.setProduct(product);
                return invoiceItem;
            }).collect(Collectors.toList());
            invoice.setItems(listItem);
        }*/

        return invoice ;
    }

    @Override
    public String getCustomer(Long id){
        Customer customer = customerClient.getCustomer(id).getBody();
        assert customer != null;

        return customer.getName();
    }

    @Override
    public String getCountry(Long id) {
        Country country = countryClient.getCountry(id).getBody();
        assert country != null;

        return country.getCountry();
    }

    @Override
    public String getSubscriber(Long id) {
        Customer customer = customerClient.getCustomer(id).getBody();
        assert customer != null;

        Country country = countryClient.getCountry(id).getBody();
        assert country != null;

        return customer.getName() + " de: " + country.getCountry();
    }

}

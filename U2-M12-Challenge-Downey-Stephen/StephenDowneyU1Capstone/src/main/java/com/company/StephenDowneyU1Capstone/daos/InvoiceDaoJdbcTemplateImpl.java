package com.company.StephenDowneyU1Capstone.daos;

import com.company.StephenDowneyU1Capstone.exceptions.JdbcOperationFailedException;
import com.company.StephenDowneyU1Capstone.models.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class InvoiceDaoJdbcTemplateImpl implements InvoiceDao{

    private static final String INSERT_INVOICE_SQL =
            "insert into invoice (name, street, city, state, zipcode, item_type, item_id, unit_price, quantity," +
                    "subtotal, tax, processing_fee, total) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,?)";
    private static final String SELECT_ALL_INVOICES_SQL =
            "select * from invoice";
    private static final String DELETE_INVOICE_SQL =
            "delete from invoice where invoice_id = ?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public InvoiceDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public Invoice saveInvoice(Invoice invoice) {
        jdbcTemplate.update(INSERT_INVOICE_SQL, invoice.getName(), invoice.getStreet(), invoice.getCity(),
                invoice.getState(), invoice.getZipcode(), invoice.getItemType(), invoice.getItemId(),
                invoice.getUnitPrice(), invoice.getQuantity(), invoice.getSubtotal(), invoice.getTax(),
                invoice.getProcessingFee(), invoice.getTotal());
        int id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        invoice.setInvoiceId(id);
        return invoice;
    }

    @Override
    public List<Invoice> findAllInvoices() {
        return jdbcTemplate.query(SELECT_ALL_INVOICES_SQL, this::mapRowToInvoice);
    }

    @Override
    public void deleteInvoice(int id) throws JdbcOperationFailedException{
        int rowsAffected =  jdbcTemplate.update(DELETE_INVOICE_SQL, id);
        if(rowsAffected == 0){
            throw new JdbcOperationFailedException("Unable to delete invoice. " + id + " is not a valid invoiceId");
        }
    }

    private Invoice mapRowToInvoice(ResultSet rs, int rowNum) throws SQLException{
        Invoice i = new Invoice();
        i.setInvoiceId(rs.getInt("invoice_id"));
        i.setName(rs.getString("name"));
        i.setStreet(rs.getString("street"));
        i.setCity(rs.getString("city"));
        i.setState(rs.getString("state"));
        i.setZipcode(rs.getString("zipcode"));
        i.setItemType(rs.getString("item_type"));
        i.setItemId(rs.getInt("item_id"));
        i.setUnitPrice(rs.getBigDecimal("unit_price"));
        i.setQuantity(rs.getInt("quantity"));
        i.setSubtotal(rs.getBigDecimal("subtotal"));
        i.setTax(rs.getBigDecimal("tax"));
        i.setProcessingFee(rs.getBigDecimal("processing_fee"));
        i.setTotal(rs.getBigDecimal("total"));
        return i;
    }
}

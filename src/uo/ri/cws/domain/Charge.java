package uo.ri.cws.domain;

import javax.persistence.*;

@Entity
@Table(name = "TCHARGES", uniqueConstraints = {
	@UniqueConstraint(columnNames = { "INVOICE_ID", "PAYMENTMEAN_ID" }) })
public class Charge extends BaseEntity {

    @ManyToOne
    private Invoice invoice;
    @ManyToOne
    private PaymentMean paymentMean;
    private double amount = 0.0;

    public Charge() {
    }

    public Charge(Invoice invoice, PaymentMean paymentMean, double amount) {
	// store the amount
	// increment the paymentMean accumulated ( paymentMean.pay( -amount) )
	// link invoice, this and paymentMean
	this.amount = amount;
	paymentMean.pay(amount);
	Associations.Charges.link(invoice, this, paymentMean);
    }

    /**
     * Unlinks this charge and restores the value to the payment mean
     * 
     * @throws IllegalStateException if the invoice is already settled
     */
    public void rewind() {
	// assert the invoice is not in PAID status
	// decrement the payment mean accumulated ( paymentMean.pay( -amount) )
	// unlink invoice, this and paymentMean
	if (!invoice.isSettled()) {
	    paymentMean.pay(-amount);
	    Associations.Charges.unlink(this);
	}
    }

    public double getImporte() {
	return amount;
    }

    protected void _setFactura(Invoice factura) {
	this.invoice = factura;
    }

    protected void _setMedioPago(PaymentMean medioPago) {
	this.paymentMean = medioPago;
    }

    public Invoice getInvoice() {
	return invoice;
    }

    public PaymentMean getPaymentMean() {
	return paymentMean;
    }

}

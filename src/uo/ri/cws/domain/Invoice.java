package uo.ri.cws.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.*;
import alb.util.assertion.Argument;
import alb.util.date.Dates;
import alb.util.math.Round;
import uo.ri.cws.domain.WorkOrder.WorkOrderStatus;

@Entity
@Table(name = "TINVOICES")
public class Invoice extends BaseEntity {
    public enum InvoiceStatus {
	NOT_YET_PAID, PAID
    }

    @Column(unique = true)
    private Long number;
    @Temporal(TemporalType.DATE)
    private Date date;
    private double amount;
    private double vat;
    @Enumerated(EnumType.STRING)
    private InvoiceStatus status = InvoiceStatus.NOT_YET_PAID;

    @OneToMany(mappedBy = "invoice")
    private Set<WorkOrder> workOrders = new HashSet<>();
    @OneToMany(mappedBy = "invoice")
    protected Set<Charge> charges = new HashSet<>();

    public Invoice() {
    }

    public Invoice(Long number) {
	super();
	Argument.isTrue(number != null);
	this.number = number;
	this.setDate(Dates.now());
    }

    public Invoice(Long number, Date date) {
	this(number);
	Argument.isTrue(date != null);
	setDate(date);
    }

    public Invoice(Long number, List<WorkOrder> workOrders) {
	this(number);
	addWorkOrders(workOrders);
    }

    public Invoice(Long number, Date date, List<WorkOrder> workOrders) {
	this(number, date);
	addWorkOrders(workOrders);
    }

    Set<Charge> _getCharges() {
	return charges;
    }

    public Set<Charge> getCharges() {
	return new HashSet<>(charges);
    }

    public Set<WorkOrder> getWorkOrders() {
	return new HashSet<>(workOrders);
    }

    public InvoiceStatus getStatus() {
	return status;
    }

    Set<WorkOrder> _getWorkOrders() {
	return workOrders;
    }

    public boolean isSettled() {
	return status == InvoiceStatus.PAID;
    }

    public double getAmount() {
	return amount;
    }

    public Date getDate() {
	return new Date(date.getTime());
    }

    public void setDate(Date newDate) {
	date = new Date(newDate.getTime());
	calcularIva();
    }

    public Long getNumber() {
	return number;
    }

    public double getVat() {
	return vat;
    }

    /**
     * Computed amount and vat (vat depends on the date)
     */
    private void computeAmount() {
	double total = 0.0;
	for (WorkOrder averia : workOrders) {
	    total += averia.getAmount();
	}
	total *= 1 + getVat() / 100;
	this.amount = Round.twoCents(total);
    }

    void calcularIva() {
	if (this.date.after(Dates.fromString("1/7/2012"))) {
	    this.vat = 21.0;
	} else {
	    this.vat = 18.0;
	}
    }

    /**
     * Adds (double links) the workOrder to the invoice and updates the amount
     * and vat
     * 
     * @param workOrder
     * @see State diagrams on the problem statement document
     * @throws IllegalStateException if the invoice status is not NOT_YET_PAID
     */
    public void addWorkOrder(WorkOrder workOrder) {
	// Verificar que la factura está en estado SIN_ABONAR
	// Verificar que La averia está TERMINADA
	// linkar factura y averia
	// marcar la averia como FACTURADA ( averia.markAsInvoiced() )
	// calcular el importe
	if (getStatus().equals(InvoiceStatus.NOT_YET_PAID)
		&& workOrder.isFinished()) {
	    Associations.ToInvoice.link(workOrder, this);
	    workOrder.markAsInvoiced();
	    computeAmount();
	} else
	    throw new IllegalStateException(
		    "Invoice paid or unfinished workorder");
    }

    public void addWorkOrders(List<WorkOrder> works) {
	for (WorkOrder w : works) {
	    if (w.getStatus().equals(WorkOrderStatus.FINISHED))
		addWorkOrder(w);
	    else
		throw new IllegalStateException("Work order not finished");

	}

    }

    /**
     * Removes a work order from the invoice and recomputes amount and vat
     * 
     * @param workOrder
     * @see State diagrams on the problem statement document
     * @throws IllegalStateException if the invoice status is not NOT_YET_PAID
     */
    public void removeWorkOrder(WorkOrder workOrder) {
	// verificar que la factura está sin abonar
	// desenlazar factura y averia
	// retornar la averia al estado FINALIZADA ( averia.markBackToFinished()
	// )
	// volver a calcular el importe
	if (status == InvoiceStatus.NOT_YET_PAID) {
	    workOrder.markBackToFinished();
	    Associations.ToInvoice.unlink(workOrder, this);
	    computeAmount();
	} else
	    throw new IllegalStateException(
		    "Factura abonada o averia sin terminar");
    }

    /**
     * Marks the invoice as PAID, but
     * 
     * @throws IllegalStateException if - Is already settled - Or the amounts
     *                               paid with charges to payment means do not
     *                               cover the total of the invoice
     */
    public void settle() {
	if (status == InvoiceStatus.PAID)
	    throw new IllegalStateException("La factura ya está abonada");

	double total = 0;
	for (Charge cargo : charges)
	    total += cargo.getImporte();

	if (total != amount)
	    throw new IllegalStateException("No hay suficientes cargos");

	status = InvoiceStatus.PAID;
    }

}

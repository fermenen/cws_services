package uo.ri.cws.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import alb.util.assertion.Argument;

@Entity
@Table(name = "TWORKORDERS", uniqueConstraints = {
	@UniqueConstraint(columnNames = { "VEHICLE_ID", "DATE" }) })
public class WorkOrder extends BaseEntity {
    public enum WorkOrderStatus {
	OPEN, ASSIGNED, FINISHED, INVOICED
    }

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    private String description;
    private double amount = 0.0;
    @Enumerated(EnumType.STRING)
    private WorkOrderStatus status = WorkOrderStatus.OPEN;

    @ManyToOne
    private Mechanic mechanic;
    @ManyToOne
    private Invoice invoice = null;
    @ManyToOne
    private Vehicle vehicle = null;

    @OneToMany(mappedBy = "workOrder")
    private Set<Intervention> interventions = new HashSet<>();

    WorkOrder() {
    }

    public WorkOrder(Vehicle vehicle) {
	super();
	Argument.isTrue(vehicle != null);
	this.date = new Date();
	Associations.Order.link(vehicle, this);
    }

    public WorkOrder(Vehicle vehicle, String description) {
	this(vehicle);
	Argument.isTrue(description != null);
	this.description = description;
    }

    void _setVehicle(Vehicle vehicle) {
	this.vehicle = vehicle;
    }

    void _setMecanico(Mechanic mecanico) {
	this.mechanic = mecanico;
    }

    public Vehicle getVehicle() {
	return vehicle;
    }

    public Invoice getInvoice() {
	return this.invoice;
    }

    public Date getDate() {
	return new Date(date.getTime());
    }

    public boolean isFinished() {
	return status == WorkOrderStatus.FINISHED;
    }

    public double getAmount() {
	return amount;
    }

    protected void _setFactura(Invoice factura) {
	this.invoice = factura;
    }

    protected void _setAmount(double amount) {
	this.amount = amount;
    }

    protected void _setDescription(String description) {
	this.description = description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public String getDescription() {
	return description;
    }

    protected Set<Intervention> _getIntervenciones() {
	return interventions;
    }

    public Set<Intervention> getInterventions() {
	return new HashSet<>(interventions);
    }

    public WorkOrderStatus getStatus() {
	return status;
    }

    public Mechanic getMechanic() {
	return mechanic;
    }

    /**
     * Changes it to INVOICED state given the right conditions This method is
     * called from Invoice.addWorkOrder(...)
     * 
     * @see State diagrams on the problem statement document
     * @throws IllegalStateException if - The work order is not FINISHED, or -
     *                               The work order is not linked with the
     *                               invoice
     */
    public void markAsInvoiced() {
	if (status != WorkOrderStatus.FINISHED || invoice == null)
	    throw new IllegalStateException("La averia no se puede facturar");

	this.status = WorkOrderStatus.INVOICED;
    }

    /**
     * Changes it to FINISHED state given the right conditions and computes the
     * amount
     * 
     * @see State diagrams on the problem statement document
     * @throws IllegalStateException if - The work order is not in ASSIGNED
     *                               state, or - The work order is not linked
     *                               with a mechanic
     */
    public void markAsFinished() {
	if (status != WorkOrderStatus.ASSIGNED || mechanic == null)
	    throw new IllegalStateException("La avería no se puede finalizar");

	amount = 0;

	for (Intervention intervencion : interventions) {
	    amount += intervencion.getAmount();
	}

	status = WorkOrderStatus.FINISHED;
    }

    /**
     * Changes it back to FINISHED state given the right conditions This method
     * is called from Invoice.removeWorkOrder(...)
     * 
     * @see State diagrams on the problem statement document
     * @throws IllegalStateException if - The work order is not INVOICED, or -
     *                               The work order is still linked with the
     *                               invoice
     */
    public void markBackToFinished() {
	if (this.status.equals(WorkOrderStatus.INVOICED) && invoice != null) {
	    Associations.ToInvoice.unlink(this, this.invoice);
	    this.status = WorkOrderStatus.FINISHED;
	} else
	    throw new IllegalStateException("La averia no está FACTURADA,"
		    + "o - la avería aún está enlazada con la" + " factura");

    }

    /**
     * Links (assigns) the work order to a mechanic and then changes its status
     * to ASSIGNED
     * 
     * @see State diagrams on the problem statement document
     * @throws IllegalStateException if - The work order is not in OPEN status,
     *                               or - The work order is already linked with
     *                               another mechanic
     */
    public void assignTo(Mechanic mechanic) {
	// Solo se puede asignar una averia que está ABIERTA
	// linkado de averia y mecanico
	// la averia pasa a ASIGNADA
	if (status == WorkOrderStatus.OPEN) {
	    Associations.Assign.link(mechanic, this);
	    this.status = WorkOrderStatus.ASSIGNED;
	}
    }

    /**
     * Unlinks (deassigns) the work order and the mechanic and then changes its
     * status back to OPEN
     * 
     * @see State diagrams on the problem statement document
     * @throws IllegalStateException if - The work order is not in ASSIGNED
     *                               status
     */
    public void desassign() {
	if (status != WorkOrderStatus.ASSIGNED || mechanic == null)
	    throw new IllegalStateException("La averia no se puede desasignar");

	this.mechanic = null;
	this.status = WorkOrderStatus.OPEN;
    }

    /**
     * In order to assign a work order to another mechanic is first have to be
     * moved back to OPEN state and unlinked from the previous mechanic.
     * 
     * @see State diagrams on the problem statement document
     * @throws IllegalStateException if - The work order is not in FINISHED
     *                               status
     */
    public void reopen() {
	// Se verifica que está en estado TERMINADA
	// Se pasa la averia a ABIERTA
	if (status == WorkOrderStatus.FINISHED)
	    status = WorkOrderStatus.OPEN;
	else
	    throw new IllegalStateException("Averia no terminada");
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((date == null) ? 0 : date.hashCode());
	result = prime * result + ((vehicle == null) ? 0 : vehicle.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	WorkOrder other = (WorkOrder) obj;
	if (date == null) {
	    if (other.date != null)
		return false;
	} else if (!date.equals(other.date))
	    return false;
	if (vehicle == null) {
	    if (other.vehicle != null)
		return false;
	} else if (!vehicle.equals(other.vehicle))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "WorkOrder [vehicle=" + vehicle + ", date=" + date
		+ ", description=" + description + ", amount=" + amount
		+ ", status=" + status + "]";
    }

}

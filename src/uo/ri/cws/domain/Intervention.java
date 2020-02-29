package uo.ri.cws.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import alb.util.assertion.Argument;

@Entity
@Table(name = "TINTERVENTIONS", uniqueConstraints = {
	@UniqueConstraint(columnNames = { "WORKORDER_ID", "MECHANIC_ID" }) })
public class Intervention extends BaseEntity {

    @ManyToOne
    private WorkOrder workOrder;
    @ManyToOne
    private Mechanic mechanic;

    private Date date;
    private int minutes;

    @OneToMany(mappedBy = "intervention")
    private Set<Substitution> sustituciones = new HashSet<Substitution>();

    public Intervention() {
    }

    public Intervention(Mechanic mechanic, WorkOrder workOrder) {
	super();
	Associations.Intervene.link(workOrder, this, mechanic);
    }

    public Intervention(Mechanic mechanic, WorkOrder workOrder, int minutes) {
	this(mechanic, workOrder);
	Argument.isTrue(minutes >= 0);
	this.minutes = minutes;
    }

    public WorkOrder getWorkOrder() {
	return workOrder;
    }

    public Mechanic getMechanic() {
	return mechanic;
    }

    public int getMinutos() {
	return minutes;
    }

    void _setAveria(WorkOrder averia) {
	this.workOrder = averia;
    }

    void _setMecanico(Mechanic mecanico) {
	this.mechanic = mecanico;
    }

    public double getAmount() {

	double precioHora = workOrder.getVehicle().getVehicleType()
		.getPricePerHour();
	double horas = ((double) minutes) / 60;
	double importe = horas * precioHora;

	for (Substitution sustitucion : sustituciones)
	    importe += sustitucion.getImporte();
	return importe;
    }

    Set<Substitution> _getSustitutions() {
	return sustituciones;
    }

    public Set<Substitution> getSustitutions() {
	return new HashSet<>(sustituciones);
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
		+ ((workOrder == null) ? 0 : workOrder.hashCode());
	result = prime * result
		+ ((mechanic == null) ? 0 : mechanic.hashCode());
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
	Intervention other = (Intervention) obj;
	if (workOrder == null) {
	    if (other.workOrder != null)
		return false;
	} else if (!workOrder.equals(other.workOrder))
	    return false;
	if (mechanic == null) {
	    if (other.mechanic != null)
		return false;
	} else if (!mechanic.equals(other.mechanic))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "Intervention [workOrder=" + workOrder + ", mechanic=" + mechanic
		+ ", date=" + date + ", minutes=" + minutes + ", sustituciones="
		+ sustituciones + "]";
    }

}

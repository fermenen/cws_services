package uo.ri.cws.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import alb.util.assertion.Argument;

@Entity
@Table(name = "TCERTIFICATES", uniqueConstraints = {
	@UniqueConstraint(columnNames = { "MECHANIC_ID", "VEHICLETYPE_ID" }) })
public class Certificate extends BaseEntity {

    @Temporal(TemporalType.DATE)
    private Date date;
    @ManyToOne
    private Mechanic mechanic;
    @ManyToOne
    private VehicleType vehicletype;

    Certificate() {
    }

    public Certificate(Mechanic mechanic, VehicleType vehicleType) {
	Argument.isTrue(mechanic != null);
	Argument.isTrue(vehicleType != null);
	this.vehicletype = vehicleType;
	this.date = new Date();
	Associations.Certificar.link(mechanic, this);
    }

    public Date getDate() {
	return new Date(date.getTime());
    }

    public Mechanic getMechanic() {
	return mechanic;
    }

    protected void _setMechanic(Mechanic mechanic) {
	this.mechanic = mechanic;
    }

    public VehicleType getVehicletype() {
	return vehicletype;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result
		+ ((mechanic == null) ? 0 : mechanic.hashCode());
	result = prime * result
		+ ((vehicletype == null) ? 0 : vehicletype.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (!super.equals(obj))
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Certificate other = (Certificate) obj;
	if (mechanic == null) {
	    if (other.mechanic != null)
		return false;
	} else if (!mechanic.equals(other.mechanic))
	    return false;
	if (vehicletype == null) {
	    if (other.vehicletype != null)
		return false;
	} else if (!vehicletype.equals(other.vehicletype))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "Certificate [date=" + date + ", mechanic=" + mechanic
		+ ", vehicletype=" + vehicletype + "]";
    }

}
